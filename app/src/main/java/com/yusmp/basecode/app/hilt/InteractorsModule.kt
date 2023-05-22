package com.yusmp.basecode.app.hilt

import com.yusmp.data.local.location.LocationInteractorImpl
import com.yusmp.domain.location.LocationInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InteractorsModule {

    @Binds
    @Singleton
    abstract fun bindLocationInteractor(impl: LocationInteractorImpl): LocationInteractor
}
