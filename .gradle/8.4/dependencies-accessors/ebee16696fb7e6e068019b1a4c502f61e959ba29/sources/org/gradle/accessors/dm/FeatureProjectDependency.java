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
public class FeatureProjectDependency extends DelegatingProjectDependency {

    @Inject
    public FeatureProjectDependency(TypeSafeProjectDependencyFactory factory, ProjectDependencyInternal delegate) {
        super(factory, delegate);
    }

    /**
     * Creates a project dependency on the project at path ":feature:auth"
     */
    public Feature_AuthProjectDependency getAuth() { return new Feature_AuthProjectDependency(getFactory(), create(":feature:auth")); }

    /**
     * Creates a project dependency on the project at path ":feature:job"
     */
    public Feature_JobProjectDependency getJob() { return new Feature_JobProjectDependency(getFactory(), create(":feature:job")); }

    /**
     * Creates a project dependency on the project at path ":feature:profile"
     */
    public Feature_ProfileProjectDependency getProfile() { return new Feature_ProfileProjectDependency(getFactory(), create(":feature:profile")); }

}
