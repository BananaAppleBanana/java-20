package com.example.java20.week3.designPattern;

public class BridgePattern {
}

class BridgeStudentService {

    private final BridgeGradeService gradeService;

    public BridgeStudentService(BridgeGradeService gradeService) {
        this.gradeService = gradeService;
    }
}

interface BridgeGradeService {}
class BridgeGradeServiceImpl1 implements BridgeGradeService {}
class BridgeGradeServiceImpl2 implements BridgeGradeService {}


