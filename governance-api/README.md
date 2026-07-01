# Governance API

사내(또는 커뮤니티) 거버넌스 제안(Proposal)을 등록하고, 투표(Vote)를 통해
찬반을 집계하여 승인/반려를 결정하는 REST API를 완성하는 문제입니다.

`Controller` / `Service` 인터페이스 / `Repository` / 도메인 모델·DTO는 이미
구성되어 있고, **`ProposalServiceImpl`의 각 메서드 구현과 예외 처리만 비어 있습니다.**
아래 요구사항에 맞게 구현하면 됩니다.

## 도메인 모델

### Proposal
| 필드 | 설명 |
|---|---|
| id | PK |
| title, description | 제안 제목/내용 |
| proposerId | 제안자 식별자 |
| status | `DRAFT` → `OPEN` → `APPROVED` \| `REJECTED` |
| quorum | 유효 표결로 인정되기 위한 최소 투표 수 |
| requiredApprovalRatio | 승인으로 인정되기 위한 최소 찬성 비율 (0.0 ~ 1.0) |
| createdAt | 생성 시각 |
| votingDeadline | 투표 마감 시각 (open 시점에 설정) |

### Vote
| 필드 | 설명 |
|---|---|
| id | PK |
| proposalId | 대상 제안 |
| voterId | 투표자 식별자 |
| choice | `APPROVE` \| `REJECT` \| `ABSTAIN` |
| votedAt | 투표 시각 |

## API 명세

| Method | URL | 설명 |
|---|---|---|
| POST | `/api/proposals` | 제안 생성 (초기 상태 `DRAFT`) |
| GET | `/api/proposals?status=` | 제안 목록 조회 (status 파라미터는 선택) |
| GET | `/api/proposals/{id}` | 제안 상세 조회 |
| POST | `/api/proposals/{id}/open` | 투표 오픈 (`DRAFT` → `OPEN`), 마감 시각 설정 |
| POST | `/api/proposals/{id}/votes` | 투표 등록 |
| POST | `/api/proposals/{id}/close` | 투표 마감 및 결과 확정 |
| GET | `/api/proposals/{id}/results` | 현재까지의 투표 집계 조회 |

## 구현해야 할 비즈니스 규칙

1. **오픈 조건**: `DRAFT` 상태의 제안만 open 할 수 있다. 이미 `OPEN`이거나
   마감된 제안을 다시 열려고 하면 `InvalidProposalStateException` (400).
2. **투표 조건**:
   - `OPEN` 상태의 제안에만 투표할 수 있다.
   - `votingDeadline`이 지난 제안에는 투표할 수 없다.
   - 한 투표자(`voterId`)는 같은 제안에 한 번만 투표할 수 있다. 중복 투표 시
     `DuplicateVoteException` (409).
3. **마감/결과 확정 (`close`)**:
   - 총 투표 수(`ABSTAIN` 포함)가 `quorum` 미만이면 정족수 미달로 `REJECTED` 처리한다.
   - 정족수를 충족했다면, 찬성 비율 = `APPROVE 수 / (APPROVE 수 + REJECT 수)`
     (`ABSTAIN`은 분모에서 제외)를 계산하여 `requiredApprovalRatio` 이상이면
     `APPROVED`, 아니면 `REJECTED`.
   - 이미 `APPROVED`/`REJECTED` 상태인 제안을 다시 close 하려 하면
     `InvalidProposalStateException` (400).
4. **존재하지 않는 제안 조회/조작 시** `ProposalNotFoundException` (404).
5. `GET /results`는 마감 여부와 관계없이 현재까지의 집계(`approveCount`,
   `rejectCount`, `abstainCount`, `totalVotes`, 현재 `status`)를 반환한다.

## 예외 처리

`exception/GlobalExceptionHandler`에 `@ExceptionHandler`를 추가하여 다음과 같이
매핑하세요.

| 예외 | HTTP Status |
|---|---|
| `ProposalNotFoundException` | 404 |
| `DuplicateVoteException` | 409 |
| `InvalidProposalStateException` | 400 |
| Bean Validation 실패 (`MethodArgumentNotValidException`) | 400 |

## 구현 범위

- [ ] `ProposalServiceImpl.createProposal`
- [ ] `ProposalServiceImpl.getProposals`
- [ ] `ProposalServiceImpl.getProposal`
- [ ] `ProposalServiceImpl.openProposal`
- [ ] `ProposalServiceImpl.castVote`
- [ ] `ProposalServiceImpl.closeProposal`
- [ ] `ProposalServiceImpl.getResults`
- [ ] `GlobalExceptionHandler` 예외 매핑

Controller와 Repository는 구조가 이미 완성되어 있으므로 수정할 필요는 없지만,
필요하다면 자유롭게 리팩터링해도 됩니다.

## 실행 방법

```bash
./mvnw spring-boot:run
```

H2 콘솔: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:governance`)

## 테스트

`src/test/java` 아래에 서비스 로직에 대한 단위 테스트를 작성해 검증하는 것을
권장합니다 (특히 정족수 미달, 중복 투표, 마감 후 투표 시도 케이스).
