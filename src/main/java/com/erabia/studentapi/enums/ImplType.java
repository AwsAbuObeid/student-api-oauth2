package com.erabia.studentapi.enums;

import com.erabia.studentapi.service.StudentService;
import com.erabia.studentapi.service.impl.*;
import com.erabia.studentapi.service.impl.StudentHibernateServiceImpl;

import java.util.HashMap;
import java.util.Map;

public enum ImplType {
    CSV(StudentCSVServiceImpl.class),
    JDBC(StudentJDBCServiceImpl.class),
    HIBERNATE(StudentHibernateServiceImpl.class);

    private final Class<? extends StudentService> strategyClass;

    private static final Map<Class<? extends StudentService>, ImplType> classToEnumMap = new HashMap<>();

    static {
        for (ImplType implType : ImplType.values())
            classToEnumMap.put(implType.getStrategyClass(), implType);
    }

    ImplType(Class<? extends StudentService> strategyClass) {
        this.strategyClass = strategyClass;
    }

    public Class<? extends StudentService> getStrategyClass() {
        return strategyClass;
    }

    public static ImplType fromClass(Class<? extends StudentService> strategyClass) {
        return classToEnumMap.get(strategyClass);
    }
}
