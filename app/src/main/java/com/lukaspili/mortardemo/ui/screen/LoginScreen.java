package com.lukaspili.mortardemo.ui.screen;

import com.lukaspili.mortardemo.app.DaggerScope;
import com.lukaspili.mortardemo.app.GlobalComponent;
import com.lukaspili.mortardemo.mortar.ScreenComponentFactory;
import com.lukaspili.mortardemo.rest.RestClient;
import com.lukaspili.mortardemo.ui.view.LoginView;

import javax.inject.Inject;

import flow.path.Path;
import mortar.ViewPresenter;
import timber.log.Timber;

/**
 * Will be included as "subscreen" in Posts screen
 *
 * @author Lukasz Piliszczuk <lukasz.pili@gmail.com>
 */
public class LoginScreen extends Path implements ScreenComponentFactory<PostsScreen.Component> {

    @Override
    public Object createComponent(PostsScreen.Component parent) {
        return DaggerLoginScreen_Component.builder()
                .component(parent)
                .build();
    }

    @dagger.Component(dependencies = PostsScreen.Component.class)
    @DaggerScope(Component.class)
    public interface Component extends GlobalComponent {
        void inject(LoginView view);
    }

    @DaggerScope(Component.class)
    public static class Presenter extends ViewPresenter<LoginView> {

        private final RestClient restClient;

        @Inject
        public Presenter(RestClient restClient) {
            this.restClient = restClient;
        }

        public void click() {
            Timber.d("Login screen clicked!");
            // do some stuff with rest client to login
        }
    }
}