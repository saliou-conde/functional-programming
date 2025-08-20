//package de.arag.functinal.programming.utility;
//
////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by FernFlower decompiler)
////
//
//package ch.sbb.tms.capaopt.techstack.architecturetests;
//
//import ch.sbb.tms.capaopt.techstack.architecturetests.rules.AnnotationRules;
//import ch.sbb.tms.capaopt.techstack.architecturetests.rules.LayeringRules;
//import ch.sbb.tms.capaopt.techstack.architecturetests.rules.StructureRules;
//import com.tngtech.archunit.core.domain.JavaClasses;
//import com.tngtech.archunit.core.importer.ClassFileImporter;
//import com.tngtech.archunit.core.importer.ImportOption;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//final class ArchitectureTest {
//    private static JavaClasses capaoptClasses;
//
//    ArchitectureTest() {
//    }
//
//    @BeforeAll
//    static void loadClasses() {
//        capaoptClasses = (new ClassFileImporter()).withImportOption(new ImportOption.DoNotIncludeTests()).withImportOptions(Exclusions.get()).importPackages(new String[]{"ch.sbb.tms"});
//    }
//
//    @Test
//    void verifyPackageStructureHasNoCycles() {
//        StructureRules.NO_CYCLES_IN_PACKAGE_STRUCTURE.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyClassesAreAssignedToLayers() {
//        LayeringRules.CLASSES_MUST_BE_ASSIGNED_TO_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyForbiddenDependenciesInModelLayer() {
//        LayeringRules.FORBIDDEN_DEPENDENCIES_IN_MODEL_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyForbiddenDependenciesInApplicationLayer() {
//        LayeringRules.FORBIDDEN_DEPENDENCIES_IN_APPLICATION_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyForbiddenDependenciesInServiceLayer() {
//        LayeringRules.FORBIDDEN_DEPENDENCIES_IN_SERVICE_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyForbidPackesInInfraLayerToBelongToOtherPackages() {
//        LayeringRules.FORBID_MULTI_PACKAGE_NAMES_INFRA.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyForbidPackesInApplicationLayerToBelongToOtherPackages() {
//        LayeringRules.FORBID_MULTI_PACKAGE_NAMES_APPLICATION.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyForbidPackesInServiceLayerToBelongToOtherPackages() {
//        LayeringRules.FORBID_MULTI_PACKAGE_NAMES_SERVICE.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyForbidPackesInModelToBelongToOtherPackages() {
//        LayeringRules.FORBID_MULTI_PACKAGE_NAMES_MODEL.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyAopMethodAnnotationsAreOnlyUsedInInfraOrAppLayer() {
//        AnnotationRules.ASPECTJ_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_APP_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyAopClassAnnotationsAreOnlyUsedInInfraOrAppLayer() {
//        AnnotationRules.ASPECTJ_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_APP_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyFeatureToggleClassAnnotationsAreOnlyUsedInInfraLayerOrRoot() {
//        AnnotationRules.FEATURE_TOGGLE_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_LAYER_OR_ROOT.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyForbiddenParameterAnnotationsMustNotBeUsed() {
//        AnnotationRules.FORBIDDEN_PARAMETER_ANNOTATIONS_MUST_NOT_BE_USED.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyForbiddenFieldAnnotationsMustNotBeUsed() {
//        AnnotationRules.FORBIDDEN_FIELD_ANNOTATIONS_MUST_NOT_BE_USED.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyForbiddenMethodAnnotationsMustNotBeUsed() {
//        AnnotationRules.FORBIDDEN_METHOD_ANNOTATIONS_MUST_NOT_BE_USED.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyForbiddenClassAnnotationsMustNotBeUsed() {
//        AnnotationRules.FORBIDDEN_CLASS_ANNOTATIONS_MUST_NOT_BE_USED.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyIntegrationComponentMethodAnnotationsAreOnlyUsedInInfraLayer() {
//        AnnotationRules.INTEGRATION_COMPONENTS_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyIntegrationComponentClassAnnotationsAreOnlyUsedInInfraLayer() {
//        AnnotationRules.INTEGRATION_COMPONENTS_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyProcessingMethodAnnotationsAreOnlyUsedInInfraAppOrServiceLayer() {
//        AnnotationRules.PROCESSING_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_APP_OR_SERVICE_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyProcessingClassAnnotationsAreOnlyUsedInInfraAppOrServiceLayer() {
//        AnnotationRules.PROCESSING_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_APP_OR_SERVICE_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyPersistenceFieldAnnotationsAreOnlyUsedInInfraLayer() {
//        AnnotationRules.PERSISTENCE_FIELDS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyPersistenceMethodAnnotationsAreOnlyUsedInInfraOrServiceLayer() {
//        AnnotationRules.PERSISTENCE_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_SERVICE_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyPersistenceClassAnnotationsAreOnlyUsedInInfraOrServiceLayer() {
//        AnnotationRules.PERSISTENCE_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_SERVICE_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyEntityClassAnnotationsAreOnlyUsedInInfraOrModelLayer() {
//        AnnotationRules.ENTITY_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_MODEL_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyConfigClassAnnotationsAreOnlyUsedInRootInfraOrAppLayer() {
//        AnnotationRules.CONFIG_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_ROOT_INFRA_OR_APP_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyConfigurationPropertiesAreOnlyUsedInRootInfraAppOrServiceLayer() {
//        AnnotationRules.CONFIGURATION_PROPERTIES_ANNOTATION_MUST_NOT_RESIDE_IN_MODEL_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyBeanMethodAnnotationsAreOnlyUsedInInfraOrAppLayer() {
//        AnnotationRules.BEAN_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_APP_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyIntegrationComponentsClassAnnotationsAreOnlyUsedInRootOrInfraLayer() {
//        AnnotationRules.INTEGRATION_CONFIGURATION_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_ROOT_OR_INFRA_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyEventListenerMethodAnnotationsAreOnlyUsedInRootInfraOrAppLayer() {
//        AnnotationRules.EVENT_LISTENER_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_ROOT_INFRA_OR_APP_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyComponentClassAnnotationsAreOnlyUsedInInfraOrAppLayer() {
//        AnnotationRules.COMPONENT_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_APP_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyServiceClassAnnotationsAreOnlyUsedInServiceLayer() {
//        AnnotationRules.SERVICE_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_SERVICE_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyControllerClassAnnotationsAreOnlyUsedInInfraLayer() {
//        AnnotationRules.CONTROLLER_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRASTRUCTURE_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifySchedulingMethodAnnotationsAreOnlyUsedInInfraOrAppLayer() {
//        AnnotationRules.SCHEDULING_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_OR_APP_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyTransactionMethodAnnotationsAreOnlyUsedInInfraAppOrServiceLayer() {
//        AnnotationRules.TRANSACTION_METHOD_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_APP_OR_SERVICE_LAYER.check(capaoptClasses);
//    }
//
//    @Test
//    void verifyTransactionClassAnnotationsAreOnlyUsedInInfraAppOrServiceLayer() {
//        AnnotationRules.TRANSACTION_CLASS_ANNOTATIONS_SHOULD_RESIDE_IN_INFRA_APP_OR_SERVICE_LAYER.check(capaoptClasses);
//    }
//}
//
