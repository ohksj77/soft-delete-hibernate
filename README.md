# soft-delete-hibernate
hibernate가 제공하는 @SoftDelete 어노테이션을 알아보자

[hibernate 6.4.4 버전 기준으로 MySQL과 함께 테스트 했으며 6.4 버전부터 도입된 어노테이션이다.]
[@SoftDelete JavaDoc](https://docs.jboss.org/hibernate/orm/6.5/javadocs/org/hibernate/annotations/SoftDelete.html)

## 다음 어노테이션을 테스트

![](https://github.com/ohksj77/soft-delete-hibernate/assets/89020004/af3c4eb3-3724-490c-903f-af7db7a1aca0)

- 디폴트로 boolean 타입의 `deleted` 필드가 추가되며, Entity 내부에 자바코드로 동일한 이름의 필드를 사용할 수 없다.
    - 중복 필드 에러가 발생한다.
    - 즉, `deleted` 필드는 자바 코드로 접근 불가하다.
- 필드명은 커스텀하게 설정 가능하며, boolean 타입이 싫은 경우 converter를 사용할 수도 있다. (후술 예정)

## JpaRepository.deleteAll() 호출시
- `deleted` = true 로 update 된다.
- 참고로, deleteAll() 은 spring-data-jpa 내부적으로 deleteById()와 같은 삭제 로직을 반복해 호출한다.

![](https://github.com/ohksj77/soft-delete-hibernate/assets/89020004/484e8b10-3880-4ab9-bc32-58409292e577)

## JpaRepository.findAll() 로 조회하는 API 호출시
- `deleted` = true 인 경우 조회되지 않는다.
- 하단 사진의 `data`가 비어있다. (envelope 패턴의 실제 데이터 부분)
  
![](https://github.com/ohksj77/soft-delete-hibernate/assets/89020004/6720975f-5fca-47c3-b906-20edc2fbdfc0)


## 커스텀한 사용
### Converter
- ~디폴트 설정에서 deleted = true 가 삭제됐음을 나타내는 것을 고려해 Boolean을 반환하면 된다.~
    - 현재 LocalDateTime.now()를 그대로 반환하면 무조건 기본으로 값이 저장됨을 확인했다. 아래 코드대로 하면 원하는대로 동작하지 않는다.


![](https://github.com/ohksj77/soft-delete-hibernate/assets/89020004/3290120d-777d-4afc-a82c-c1199872cb4f)

### Entity 설정
![](https://github.com/ohksj77/soft-delete-hibernate/assets/89020004/7fddbcc8-fda0-4dff-b6bd-a3a3fcf647f8)

### datetime 타입의 deleted_at 필드 생성
- ~api 호출시 JpaRepository는 deleted_at이 null인 부분만 조회를 수행함을 확인했다.~
    - ~잘못된 테스트였다.~
  
![](https://github.com/ohksj77/soft-delete-hibernate/assets/89020004/2296993e-b73f-4920-b3c5-7ba57453221a)

## 삭제가 된 부분을 jpa를 통해 조회가 가능할까?
- 적절히 jpa의 쿼리를 조작하면 가능하다.

- 필드가 명시되어 있지 않기에 그냥 jpql으로는 불가하다.

![](https://github.com/ohksj77/soft-delete-hibernate/assets/89020004/8c995c4e-1173-4c06-9f26-c0839e129e26)

- nativeQuery = true 를 통해 쿼리를 작성하면

![](https://github.com/ohksj77/soft-delete-hibernate/assets/89020004/c6dae11f-12a4-4eec-ba52-4382fc3d6ea8)

- 쿼리가 원하는대로 실행되며

![](https://github.com/ohksj77/soft-delete-hibernate/assets/89020004/7e582269-5602-4bb8-a797-38ac80a50fd9)

- deleted_at이 null이 아닌 row에 대한 조회 결과를 얻을 수 있다.

![](https://github.com/ohksj77/soft-delete-hibernate/assets/89020004/ffc0c129-311e-42e2-b929-a8023f2f5fd2)
