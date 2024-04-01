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
public class RootProjectAccessor extends TypeSafeProjectDependencyFactory {


    @Inject
    public RootProjectAccessor(DefaultProjectDependencyFactory factory, ProjectFinder finder) {
        super(factory, finder);
    }

    /**
     * Creates a project dependency on the project at path ":"
     */
    public SeekDemoProjectDependency getSeekDemo() { return new SeekDemoProjectDependency(getFactory(), create(":")); }

    /**
     * Creates a project dependency on the project at path ":app"
     */
    public AppProjectDependency getApp() { return new AppProjectDependency(getFactory(), create(":app")); }

    /**
     * Creates a project dependency on the project at path ":common"
     */
    public CommonProjectDependency getCommon() { return new CommonProjectDependency(getFactory(), create(":common")); }

    /**
     * Creates a project dependency on the project at path ":feature"
     */
    public FeatureProjectDependency getFeature() { return new FeatureProjectDependency(getFactory(), create(":feature")); }

    /**
     * Creates a project dependency on the project at path ":network"
     */
    public NetworkProjectDependency getNetwork() { return new NetworkProjectDependency(getFactory(), create(":network")); }

    /**
     * Creates a project dependency on the project at path ":storage"
     */
    public StorageProjectDependency getStorage() { return new StorageProjectDependency(getFactory(), create(":storage")); }

    /**
     * Creates a project dependency on the project at path ":theme"
     */
    public ThemeProjectDependency getTheme() { return new ThemeProjectDependency(getFactory(), create(":theme")); }

}
