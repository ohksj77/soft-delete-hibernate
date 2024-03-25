package com.soft.deletion.greeting;

public record GreetingResponse(
        Long id, Boolean isEveryoneGreetingTheOther, Boolean isEveryoneHavingGoodDay) {}
