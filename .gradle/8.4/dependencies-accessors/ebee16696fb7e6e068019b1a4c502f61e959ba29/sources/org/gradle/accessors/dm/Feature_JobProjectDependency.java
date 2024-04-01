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
public class Feature_JobProjectDependency extends DelegatingProjectDependency {

    @Inject
    public Feature_JobProjectDependency(TypeSafeProjectDependencyFactory factory, ProjectDependencyInternal delegate) {
        super(factory, delegate);
    }

    /**
     * Creates a project dependency on the project at path ":feature:job:collection"
     */
    public Feature_Job_CollectionProjectDependency getCollection() { return new Feature_Job_CollectionProjectDependency(getFactory(), create(":feature:job:collection")); }

    /**
     * Creates a project dependency on the project at path ":feature:job:data"
     */
    public Feature_Job_DataProjectDependency getData() { return new Feature_Job_DataProjectDependency(getFactory(), create(":feature:job:data")); }

    /**
     * Creates a project dependency on the project at path ":feature:job:detail"
     */
    public Feature_Job_DetailProjectDependency getDetail() { return new Feature_Job_DetailProjectDependency(getFactory(), create(":feature:job:detail")); }

}
