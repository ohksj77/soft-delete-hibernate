# soft-delete-hibernate
hibernate가 제공하는 @SoftDelete 어노테이션을 알아보자
[@SoftDelete JavaDoc](https://docs.jboss.org/hibernate/orm/6.5/javadocs/org/hibernate/annotations/SoftDelete.html)

## 다음 어노테이션을 테스트

![](https://github.com/ohksj77/soft-delete-hibernate/assets/89020004/af3c4eb3-3724-490c-903f-af7db7a1aca0)

- 디폴트로 boolean 타입의 `deleted` 필드가 추가되며, Entity 내부에 자바코드로 동일한 이름의 필드를 사용할 수 없다.
    - 중복 필드 에러가 발생한다.
    - 즉, 자바 코드로 접근은 불가하다.
- 필드명은 커스텀하게 설정 가능하며, boolean 타입이 싫은 경우 converter를 사용할 수도 있다.

## JpaRepository.deleteAll() 호출시
- `deleted` = true 로 update 된다.
- 참고로, deleteAll() 은 spring-data-jpa 내부적으로 deleteById()와 같은 삭제 로직을 반복해 호출한다.

![](https://github.com/ohksj77/soft-delete-hibernate/assets/89020004/484e8b10-3880-4ab9-bc32-58409292e577)

## JpaRepository.findAll() 로 조회하는 API 호출시
- `deleted` = true 인 경우 조회되지 않는다.
- 하단 사진의 `data`가 비어있다. (envelop 패턴의 실제 데이터 부분)
  
![](https://github.com/ohksj77/soft-delete-hibernate/assets/89020004/6720975f-5fca-47c3-b906-20edc2fbdfc0)
