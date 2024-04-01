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
public class CommonProjectDependency extends DelegatingProjectDependency {

    @Inject
    public CommonProjectDependency(TypeSafeProjectDependencyFactory factory, ProjectDependencyInternal delegate) {
        super(factory, delegate);
    }

    /**
     * Creates a project dependency on the project at path ":common:data"
     */
    public Common_DataProjectDependency getData() { return new Common_DataProjectDependency(getFactory(), create(":common:data")); }

    /**
     * Creates a project dependency on the project at path ":common:domain"
     */
    public Common_DomainProjectDependency getDomain() { return new Common_DomainProjectDependency(getFactory(), create(":common:domain")); }

    /**
     * Creates a project dependency on the project at path ":common:utils"
     */
    public Common_UtilsProjectDependency getUtils() { return new Common_UtilsProjectDependency(getFactory(), create(":common:utils")); }

}
