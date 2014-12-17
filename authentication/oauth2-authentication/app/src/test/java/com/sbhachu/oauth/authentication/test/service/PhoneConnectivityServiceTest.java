package com.sbhachu.oauth.authentication.test.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.sbhachu.oauth.authentication.service.PhoneConnectivityService_;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Created by sbhachu on 13/12/2014.
 */
@RunWith(MockitoJUnitRunner.class)
public class PhoneConnectivityServiceTest {
    @Mock
    public Context context;

    @Mock
    public ConnectivityManager connectivityManager;

    @Mock
    public NetworkInfo networkInfo;

    public PhoneConnectivityService_ phoneConnectivityService;

    @Before
    public void setUp() throws Exception {
        phoneConnectivityService = PhoneConnectivityService_.getInstance_(context);
    }

    @Test
    public void shouldBeOfflineIfActiveNetworkIsNull() throws Exception {
        // given
        given(context.getSystemService(Context.CONNECTIVITY_SERVICE)).willReturn(connectivityManager);
        given(connectivityManager.getActiveNetworkInfo()).willReturn(null);

        // when
        final boolean online = phoneConnectivityService.isOnline();

        // then
        assertThat(online).isFalse();
    }

    @Test
    public void shouldBeOnlineIfActiveNetworkIsConnectedOrConnecting() throws Exception {
        // given
        given(context.getSystemService(Context.CONNECTIVITY_SERVICE)).willReturn(connectivityManager);
        given(connectivityManager.getActiveNetworkInfo()).willReturn(networkInfo);
        given(networkInfo.isConnectedOrConnecting()).willReturn(true);

        // when
        final boolean online = phoneConnectivityService.isOnline();

        // then
        assertThat(online).isTrue();
    }
}
