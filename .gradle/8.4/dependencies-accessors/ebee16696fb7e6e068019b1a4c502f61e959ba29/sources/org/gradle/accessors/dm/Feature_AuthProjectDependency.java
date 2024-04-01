package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.ProjectDependency;
import org.gradle.api.internal.artifacts.dependencies.ProjectDependencyInternal;
import org.gradle.api.internal.artifacts.DefaultProjectDependencyFactory;
import org.gradle.api.internal.artifacts.dsl.dependencies.ProjectFinder;
import org.gradle.api.internal.catalog.DelegatingProjectDependency;
import org.gradle.api.internal.catalog.TypeSafeProjectDependencyFactory;
import javax.inject.Inject;

@NonNullApi
public class Feature_AuthProjectDependency extends DelegatingProjectDependency {

    @Inject
    public Feature_AuthProjectDependency(TypeSafeProjectDependencyFactory factory, ProjectDependencyInternal delegate) {
        super(factory, delegate);
    }

    /**
     * Creates a project dependency on the project at path ":feature:auth:data"
     */
    public Feature_Auth_DataProjectDependency getData() { return new Feature_Auth_DataProjectDependency(getFactory(), create(":feature:auth:data")); }

    /**
     * Creates a project dependency on the project at path ":feature:auth:domain"
     */
    public Feature_Auth_DomainProjectDependency getDomain() { return new Feature_Auth_DomainProjectDependency(getFactory(), create(":feature:auth:domain")); }

    /**
     * Creates a project dependency on the project at path ":feature:auth:presentation"
     */
    public Feature_Auth_PresentationProjectDependency getPresentation() { return new Feature_Auth_PresentationProjectDependency(getFactory(), create(":feature:auth:presentation")); }

}
