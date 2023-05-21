package com.yusmp.basecode.presentation.profile

import com.yusmp.basecode.presentation.common.UiEvent
import com.yusmp.basecode.presentation.common.UiState

data class ProfileUiState(
    val phoneNumber: String? = null,
    val isLoading: Boolean = true,
    val isUserAuthorized: Boolean = false,
) : UiState

sealed class ProfileEvent : UiEvent() {
    object NavigateToAuthorization : ProfileEvent()
}