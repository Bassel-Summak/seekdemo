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
public class Feature_Job_CollectionProjectDependency extends DelegatingProjectDependency {

    @Inject
    public Feature_Job_CollectionProjectDependency(TypeSafeProjectDependencyFactory factory, ProjectDependencyInternal delegate) {
        super(factory, delegate);
    }

    /**
     * Creates a project dependency on the project at path ":feature:job:collection:domain"
     */
    public Feature_Job_Collection_DomainProjectDependency getDomain() { return new Feature_Job_Collection_DomainProjectDependency(getFactory(), create(":feature:job:collection:domain")); }

    /**
     * Creates a project dependency on the project at path ":feature:job:collection:presentation"
     */
    public Feature_Job_Collection_PresentationProjectDependency getPresentation() { return new Feature_Job_Collection_PresentationProjectDependency(getFactory(), create(":feature:job:collection:presentation")); }

}
