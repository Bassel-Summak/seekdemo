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
public class Feature_Job_DetailProjectDependency extends DelegatingProjectDependency {

    @Inject
    public Feature_Job_DetailProjectDependency(TypeSafeProjectDependencyFactory factory, ProjectDependencyInternal delegate) {
        super(factory, delegate);
    }

    /**
     * Creates a project dependency on the project at path ":feature:job:detail:domain"
     */
    public Feature_Job_Detail_DomainProjectDependency getDomain() { return new Feature_Job_Detail_DomainProjectDependency(getFactory(), create(":feature:job:detail:domain")); }

    /**
     * Creates a project dependency on the project at path ":feature:job:detail:presentation"
     */
    public Feature_Job_Detail_PresentationProjectDependency getPresentation() { return new Feature_Job_Detail_PresentationProjectDependency(getFactory(), create(":feature:job:detail:presentation")); }

}
