package de.arag.functinal.programming.utility;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

//package ch.sbb.tms.capaopt.techstack.architecturetests.rules;

import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.lang.syntax.elements.ClassesShouldConjunction;
import com.tngtech.archunit.lang.syntax.elements.FieldsShouldConjunction;
import com.tngtech.archunit.lang.syntax.elements.GivenClassesConjunction;
import com.tngtech.archunit.lang.syntax.elements.GivenFieldsConjunction;
import com.tngtech.archunit.lang.syntax.elements.GivenMethodsConjunction;
import com.tngtech.archunit.lang.syntax.elements.MethodsShouldConjunction;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public final class AnnotationRules {
    private static final String[] ASPECTJ_METHOD_ANNOTATIONS = new String[]{"org.aspectj.lang.annotation.After", "org.aspectj.lang.annotation.AfterReturning", "org.aspectj.lang.annotation.AfterThrowing", "org.aspectj.lang.annotation.Around", "org.aspectj.lang.annotation.Before", "org.aspectj.lang.annotation.Pointcut"};
    private static final String[] FEATURE_TOGGLES = new String[]{"org.springframework.batch.core.configuration.annotation.EnableBatchProcessing", "org.springframework.context.annotation.EnableAspectJAutoProxy", "org.springframework.cache.annotation.EnableCaching", "org.springframework.scheduling.annotation.EnableAsync", "org.springframework.scheduling.annotation.EnableScheduling", "org.springframework.data.jpa.repository.config.EnableJpaAuditing", "org.springframework.data.jpa.repository.config.EnableJpaRepositories", "org.springframework.data.web.config.EnableSpringDataWebSupport", "org.springframework.integration.config.EnableIntegration", "org.springframework.integration.config.EnableIntegrationManagement", "org.springframework.integration.config.EnableMessageHistory", "org.springframework.integration.config.EnablePublisher", "org.springframework.web.servlet.config.annotation.EnableWebMvc"};
    private static final String[] FORBIDDEN_PARAMETER_ANNOTATIONS = new String[]{"org.springframework.beans.factory.annotation.Value"};
    private static final String[] FORBIDDEN_FIELD_ANNOTATIONS = new String[]{"org.springframework.beans.factory.annotation.Autowired", "org.springframework.beans.factory.annotation.Value"};
    private static final String[] FORBIDDEN_METHOD_ANNOTATIONS = new String[]{"org.springframework.context.annotation.Primary"};
    private static final String[] FORBIDDEN_CLASS_ANNOTATIONS = new String[]{"org.springframework.context.annotation.Primary"};
    private static final String[] INTEGRATION_METHOD_ANNOTATIONS = new String[]{"org.springframework.integration.annotation.Aggregator", "org.springframework.integration.annotation.BridgeFrom", "org.springframework.integration.annotation.BridgeTo", "org.springframework.integration.annotation.Filter", "org.springframework.integration.annotation.Gateway", "org.springframework.integration.annotation.InboundChannelAdapter", "org.springframework.integration.annotation.Publisher", "org.springframework.integration.annotation.Router", "org.springframework.integration.annotation.ServiceActivator", "org.springframework.integration.annotation.Splitter", "org.springframework.integration.annotation.Transformer"};
    private static final String[] INTEGRATION_CLASS_ANNOTATIONS = new String[]{"org.springframework.integration.annotation.MessageEndpoint", "org.springframework.integration.annotation.MessagingGateway", "org.springframework.integration.annotation.Publisher"};
    private static final String[] PROCESSING_LAYERS_METHOD_ANNOTATIONS = new String[]{"org.springframework.scheduling.annotation.Async", "org.springframework.cache.annotation.Cacheable", "org.springframework.cache.annotation.CacheEvict", "org.springframework.cache.annotation.CachePut", "org.springframework.cache.annotation.Caching", "org.springframework.transaction.annotation.Transactional", "jakarta.transaction.Transactional"};
    private static final String[] PROCESSING_LAYERS_CLASS_ANNOTATIONS = new String[]{"org.springframework.scheduling.annotation.Async", "org.springframework.cache.annotation.Cacheable", "org.springframework.cache.annotation.CacheEvict", "org.springframework.cache.annotation.CachePut", "org.springframework.cache.annotation.Caching", "org.springframework.transaction.annotation.Transactional", "jakarta.transaction.Transactional"};
    private static final String[] PERSISTENCE_CLASS_ANNOTATIONS = new String[]{"org.springframework.stereotype.Repository", "org.springframework.data.repository.NoRepositoryBean", "jakarta.persistence.NamedNativeQueries", "jakarta.persistence.NamedNativeQuery", "jakarta.persistence.NamedQueries", "jakarta.persistence.NamedQuery", "jakarta.persistence.NamedStoredProcedureQueries", "jakarta.persistence.NamedStoredProcedureQuery", "jakarta.persistence.NamedNativeQueries", "jakarta.persistence.NamedNativeQuery", "jakarta.persistence.NamedQueries", "jakarta.persistence.NamedQuery"};
    private static final String[] ENTITY_CLASS_ANNOTATIONS = new String[]{"jakarta.persistence.Entity", "jakarta.persistence.MappedSuperclass", "org.springframework.data.mongodb.core.mapping.Document"};
    private static final String[] CONFIG_CLASS_ANNOTATIONS = new String[]{"org.springframework.boot.context.properties.ConfigurationPropertiesScan", "org.springframework.context.annotation.ComponentScan", "org.springframework.context.annotation.ComponentScans", "org.springframework.context.annotation.Configuration"};
    private static final String[] SCHEDULE_METHOD_ANNOTATIONS = new String[]{"org.springframework.scheduling.annotation.Scheduled", "org.springframework.scheduling.annotation.Schedules"};
    private static final String[] TRANSACTION_ANNOTATIONS = new String[]{"org.springframework.transaction.annotation.Transactional", "jakarta.transaction.Transactional"};
    private static final String[] CONTROLLER_CLASS_ANNOTATIONS = new String[]{"org.springframework.stereotype.Controller", "org.springframework.web.bind.annotation.ControllerAdvice", "org.springframework.web.bind.annotation.RestController"};
    public static final ArchRule ASPECTJ_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_APP_LAYER;
    public static final ArchRule ASPECTJ_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_APP_LAYER;
    public static final ArchRule FEATURE_TOGGLE_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_LAYER_OR_ROOT;
    public static final ArchRule FORBIDDEN_PARAMETER_ANNOTATIONS_MUST_NOT_BE_USED;
    public static final ArchRule FORBIDDEN_FIELD_ANNOTATIONS_MUST_NOT_BE_USED;
    public static final ArchRule FORBIDDEN_METHOD_ANNOTATIONS_MUST_NOT_BE_USED;
    public static final ArchRule FORBIDDEN_CLASS_ANNOTATIONS_MUST_NOT_BE_USED;
    public static final ArchRule INTEGRATION_COMPONENTS_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_LAYER;
    public static final ArchRule INTEGRATION_COMPONENTS_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_LAYER;
    public static final ArchRule PROCESSING_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_APP_OR_SERVICE_LAYER;
    public static final ArchRule PROCESSING_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_APP_OR_SERVICE_LAYER;
    public static final ArchRule PERSISTENCE_FIELDS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_LAYER;
    public static final ArchRule PERSISTENCE_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_SERVICE_LAYER;
    public static final ArchRule PERSISTENCE_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_SERVICE_LAYER;
    public static final ArchRule ENTITY_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_MODEL_LAYER;
    public static final ArchRule CONFIG_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_ROOT_INFRA_OR_APP_LAYER;
    public static final ArchRule CONFIGURATION_PROPERTIES_ANNOTATION_MUST_NOT_RESIDE_IN_MODEL_LAYER;
    public static final ArchRule BEAN_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_APP_LAYER;
    public static final ArchRule INTEGRATION_CONFIGURATION_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_ROOT_OR_INFRA_LAYER;
    public static final ArchRule EVENT_LISTENER_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_ROOT_INFRA_OR_APP_LAYER;
    public static final ArchRule COMPONENT_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_APP_LAYER;
    public static final ArchRule SERVICE_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_SERVICE_LAYER;
    public static final ArchRule CONTROLLER_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRASTRUCTURE_LAYER;
    public static final ArchRule SCHEDULING_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_APP_LAYER;
    public static final ArchRule TRANSACTION_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_APP_OR_SERVICE_LAYER;
    public static final ArchRule TRANSACTION_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_APP_OR_SERVICE_LAYER;

    private AnnotationRules() {
    }

    private static ArchCondition<? super JavaMethod> notHaveParametersAnnotatedWith(String[] forbiddenParameterAnnotations) {
        String caption = (String)Arrays.stream(forbiddenParameterAnnotations).map((s) -> {
            return s.substring(s.lastIndexOf(".") + 1);
        }).map((s) -> {
            return "not have parameters annotated with @" + s;
        }).collect(Collectors.joining(" and "));
        final Map<String, String> forbiddenAnnotations = (Map)Arrays.stream(forbiddenParameterAnnotations).collect(Collectors.toMap((s) -> {
            return s;
        }, (s) -> {
            String var10000 = s.substring(s.lastIndexOf(".") + 1);
            return "@" + var10000;
        }));
        return new ArchCondition<JavaMethod>(caption, new Object[0]) {
            public void check(JavaMethod method, ConditionEvents events) {
                Annotation[][] var3 = method.reflect().getParameterAnnotations();
                int var4 = var3.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    Annotation[] parameterAnnotations = var3[var5];
                    Annotation[] var7 = parameterAnnotations;
                    int var8 = parameterAnnotations.length;

                    for(int var9 = 0; var9 < var8; ++var9) {
                        Annotation annotation = var7[var9];
                        String annotationClass = annotation.annotationType().getName();
                        if (forbiddenAnnotations.containsKey(annotationClass)) {
                            String var10002 = method.getDescription();
                            events.add(SimpleConditionEvent.violated(method, var10002 + " has parameters annotated with " + (String)forbiddenAnnotations.get(annotationClass)));
                        }
                    }
                }

            }
        };
    }

    private static ArchRule classesShouldNotBeAnnotatedWith(String[] forbiddenClassAnnotations) {
        ClassesShouldConjunction classConjunction = ArchRuleDefinition.classes().should().notBeAnnotatedWith(forbiddenClassAnnotations[0]);

        for(int i = 1; i < forbiddenClassAnnotations.length; ++i) {
            String annotationClass = forbiddenClassAnnotations[i];
            classConjunction = classConjunction.andShould().notBeAnnotatedWith(annotationClass);
        }

        return classConjunction;
    }

    private static ArchRule methodsShouldNotBeAnnotatedWith(String[] forbiddenMethodAnnotations) {
        MethodsShouldConjunction methodConjunction = (MethodsShouldConjunction)ArchRuleDefinition.methods().should().notBeAnnotatedWith(forbiddenMethodAnnotations[0]);

        for(int i = 1; i < forbiddenMethodAnnotations.length; ++i) {
            String annotationClass = forbiddenMethodAnnotations[i];
            methodConjunction = (MethodsShouldConjunction)methodConjunction.andShould().notBeAnnotatedWith(annotationClass);
        }

        return methodConjunction;
    }

    private static ArchRule fieldsShouldNotBeAnnotatedWith(String[] forbiddenFieldAnnotations) {
        FieldsShouldConjunction fieldConjunction = (FieldsShouldConjunction)ArchRuleDefinition.fields().should().notBeAnnotatedWith(forbiddenFieldAnnotations[0]);

        for(int i = 1; i < forbiddenFieldAnnotations.length; ++i) {
            String annotationClass = forbiddenFieldAnnotations[i];
            fieldConjunction = (FieldsShouldConjunction)fieldConjunction.andShould().notBeAnnotatedWith(annotationClass);
        }

        return fieldConjunction;
    }

    private static GivenClassesConjunction classesAnnotatedWith(String... annotationClasses) {
        GivenClassesConjunction classesConjunction = (GivenClassesConjunction)ArchRuleDefinition.classes().that().areAnnotatedWith(annotationClasses[0]);

        for(int i = 1; i < annotationClasses.length; ++i) {
            String annotationClass = annotationClasses[i];
            classesConjunction = (GivenClassesConjunction)classesConjunction.or().areAnnotatedWith(annotationClass);
        }

        return classesConjunction;
    }

    private static GivenMethodsConjunction methodsAnnotatedWith(String... annotationClasses) {
        GivenMethodsConjunction methodConjunction = (GivenMethodsConjunction)ArchRuleDefinition.methods().that().areAnnotatedWith(annotationClasses[0]);

        for(int i = 1; i < annotationClasses.length; ++i) {
            String annotationClass = annotationClasses[i];
            methodConjunction = (GivenMethodsConjunction)methodConjunction.or().areAnnotatedWith(annotationClass);
        }

        return methodConjunction;
    }

    static {
        ASPECTJ_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_APP_LAYER = (ArchRule)methodsAnnotatedWith(ASPECTJ_METHOD_ANNOTATIONS).should().beDeclaredInClassesThat().resideInAnyPackage(new String[]{"ch.sbb.tms..infra..", "ch.sbb.tms..app.."});
        ASPECTJ_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_APP_LAYER = classesAnnotatedWith("org.aspectj.lang.annotation.Aspect").should().resideInAnyPackage(new String[]{"ch.sbb.tms..infra..", "ch.sbb.tms..app.."});
        FEATURE_TOGGLE_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_LAYER_OR_ROOT = classesAnnotatedWith(FEATURE_TOGGLES).should().resideInAPackage("ch.sbb.tms..infra..").orShould().beAnnotatedWith("org.springframework.boot.autoconfigure.SpringBootApplication");
        FORBIDDEN_PARAMETER_ANNOTATIONS_MUST_NOT_BE_USED = ArchRuleDefinition.methods().should(notHaveParametersAnnotatedWith(FORBIDDEN_PARAMETER_ANNOTATIONS));
        FORBIDDEN_FIELD_ANNOTATIONS_MUST_NOT_BE_USED = fieldsShouldNotBeAnnotatedWith(FORBIDDEN_FIELD_ANNOTATIONS);
        FORBIDDEN_METHOD_ANNOTATIONS_MUST_NOT_BE_USED = methodsShouldNotBeAnnotatedWith(FORBIDDEN_METHOD_ANNOTATIONS);
        FORBIDDEN_CLASS_ANNOTATIONS_MUST_NOT_BE_USED = classesShouldNotBeAnnotatedWith(FORBIDDEN_CLASS_ANNOTATIONS);
        INTEGRATION_COMPONENTS_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_LAYER = (ArchRule)methodsAnnotatedWith(INTEGRATION_METHOD_ANNOTATIONS).should().beDeclaredInClassesThat().resideInAPackage("ch.sbb.tms..infra..");
        INTEGRATION_COMPONENTS_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_LAYER = classesAnnotatedWith(INTEGRATION_CLASS_ANNOTATIONS).should().resideInAPackage("ch.sbb.tms..infra..");
        PROCESSING_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_APP_OR_SERVICE_LAYER = (ArchRule)methodsAnnotatedWith(PROCESSING_LAYERS_METHOD_ANNOTATIONS).should().beDeclaredInClassesThat().resideInAnyPackage(new String[]{"ch.sbb.tms..infra..", "ch.sbb.tms..app..", "ch.sbb.tms..service.."});
        PROCESSING_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_APP_OR_SERVICE_LAYER = classesAnnotatedWith(PROCESSING_LAYERS_CLASS_ANNOTATIONS).should().resideInAnyPackage(new String[]{"ch.sbb.tms..infra..", "ch.sbb.tms..app..", "ch.sbb.tms..service.."});
        PERSISTENCE_FIELDS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_LAYER = (ArchRule)((GivenFieldsConjunction)ArchRuleDefinition.fields().that().areAnnotatedWith("jakarta.persistence.PersistenceContext")).should().beDeclaredInClassesThat().resideInAnyPackage(new String[]{"ch.sbb.tms..infra.."});
        PERSISTENCE_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_SERVICE_LAYER = (ArchRule)methodsAnnotatedWith("org.springframework.data.jpa.repository.Query").should().beDeclaredInClassesThat().resideInAnyPackage(new String[]{"ch.sbb.tms..infra..", "ch.sbb.tms..service.."});
        PERSISTENCE_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_SERVICE_LAYER = classesAnnotatedWith(PERSISTENCE_CLASS_ANNOTATIONS).should().resideInAnyPackage(new String[]{"ch.sbb.tms..infra..", "ch.sbb.tms..service.."});
        ENTITY_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_MODEL_LAYER = classesAnnotatedWith(ENTITY_CLASS_ANNOTATIONS).should().resideInAnyPackage(new String[]{"ch.sbb.tms..infra..", "ch.sbb.tms..model.."});
        CONFIG_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_ROOT_INFRA_OR_APP_LAYER = classesAnnotatedWith(CONFIG_CLASS_ANNOTATIONS).should().beAnnotatedWith("org.springframework.boot.autoconfigure.SpringBootApplication").orShould().resideInAnyPackage(new String[]{"ch.sbb.tms..infra..", "ch.sbb.tms..app.."});
        CONFIGURATION_PROPERTIES_ANNOTATION_MUST_NOT_RESIDE_IN_MODEL_LAYER = classesAnnotatedWith("org.springframework.boot.context.properties.ConfigurationProperties").should().beAnnotatedWith("org.springframework.boot.autoconfigure.SpringBootApplication").orShould().resideInAnyPackage(new String[]{"ch.sbb.tms..infra..", "ch.sbb.tms..app..", "ch.sbb.tms..service.."});
        BEAN_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_APP_LAYER = (ArchRule)methodsAnnotatedWith("org.springframework.context.annotation.Bean").should().beDeclaredInClassesThat().resideInAnyPackage(new String[]{"ch.sbb.tms..infra..", "ch.sbb.tms..app.."});
        INTEGRATION_CONFIGURATION_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_ROOT_OR_INFRA_LAYER = classesAnnotatedWith("org.springframework.integration.annotation.IntegrationComponentScan").should().beAnnotatedWith("org.springframework.boot.autoconfigure.SpringBootApplication").orShould().resideInAPackage("ch.sbb.tms..infra..");
        EVENT_LISTENER_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_ROOT_INFRA_OR_APP_LAYER = (ArchRule)((MethodsShouldConjunction)methodsAnnotatedWith("org.springframework.context.event.EventListener").should().beDeclaredInClassesThat().areAnnotatedWith("org.springframework.boot.autoconfigure.SpringBootApplication")).orShould().beDeclaredInClassesThat().resideInAnyPackage(new String[]{"ch.sbb.tms..infra..", "ch.sbb.tms..app.."});
        COMPONENT_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_APP_LAYER = classesAnnotatedWith("org.springframework.stereotype.Component").should().resideInAnyPackage(new String[]{"ch.sbb.tms..infra..", "ch.sbb.tms..app.."});
        SERVICE_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_SERVICE_LAYER = classesAnnotatedWith("org.springframework.stereotype.Service").should().resideInAnyPackage(new String[]{"ch.sbb.tms..service.."});
        CONTROLLER_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRASTRUCTURE_LAYER = classesAnnotatedWith(CONTROLLER_CLASS_ANNOTATIONS).should().resideInAnyPackage(new String[]{"ch.sbb.tms..infra.."});
        SCHEDULING_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_APP_LAYER = (ArchRule)methodsAnnotatedWith(SCHEDULE_METHOD_ANNOTATIONS).should().beDeclaredInClassesThat().resideInAnyPackage(new String[]{"ch.sbb.tms..infra..", "ch.sbb.tms..app.."});
        TRANSACTION_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_APP_OR_SERVICE_LAYER = (ArchRule)methodsAnnotatedWith(TRANSACTION_ANNOTATIONS).should().beDeclaredInClassesThat().resideInAnyPackage(new String[]{"ch.sbb.tms..infra..", "ch.sbb.tms..app..", "ch.sbb.tms..service.."});
        TRANSACTION_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_APP_OR_SERVICE_LAYER = classesAnnotatedWith(TRANSACTION_ANNOTATIONS).should().resideInAnyPackage(new String[]{"ch.sbb.tms..infra..", "ch.sbb.tms..app..", "ch.sbb.tms..service.."});
    }
}

