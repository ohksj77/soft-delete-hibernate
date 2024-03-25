package com.soft.deletion.greeting;

import com.soft.deletion.global.audit.AuditListener;
import com.soft.deletion.global.audit.Auditable;
import com.soft.deletion.global.audit.BaseTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.*;

import org.hibernate.annotations.SoftDelete;

@Entity
@SoftDelete // hibernate 지원 annotation
@EntityListeners(AuditListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Greeting implements Auditable {

    @Getter @Id @GeneratedValue private Long id;

    private Boolean howAreYou;
    private Boolean iAmFine;
    private Boolean thankYouAndYou;
    private Boolean iAmFineThankYouSoMuch;

    @Setter @Getter private BaseTime baseTime;

    @Builder
    private Greeting(
            final Boolean howAreYou,
            final Boolean iAmFine,
            final Boolean thankYouAndYou,
            final Boolean iAmFineThankYouSoMuch) {
        this.howAreYou = howAreYou;
        this.iAmFine = iAmFine;
        this.thankYouAndYou = thankYouAndYou;
        this.iAmFineThankYouSoMuch = iAmFineThankYouSoMuch;
    }

    public static Greeting ofOnlyIAmFine() {
        return Greeting.builder()
                .howAreYou(true)
                .iAmFine(true)
                .thankYouAndYou(true)
                .iAmFineThankYouSoMuch(false)
                .build();
    }

    public static Greeting ofOnlyYouAreFine() {
        return Greeting.builder()
                .howAreYou(true)
                .iAmFine(false)
                .thankYouAndYou(true)
                .iAmFineThankYouSoMuch(true)
                .build();
    }

    public boolean isEveryoneGreetingTheOther() { // 모두가 서로 인사를 건냈는가
        return this.howAreYou && this.thankYouAndYou;
    }

    public boolean isEveryoneHavingGoodDay() { // 모두가 좋은 하루를 보내고 있는가
        return this.iAmFine && this.iAmFineThankYouSoMuch;
    }

    public void updateToBeHappy() { // 강제로 모두 Happy 하도록 업데이트
        this.iAmFine = true;
        this.iAmFineThankYouSoMuch = true;
    }
}
