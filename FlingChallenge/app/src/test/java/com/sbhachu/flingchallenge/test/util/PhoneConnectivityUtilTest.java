package com.sbhachu.flingchallenge.test.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.sbhachu.flingchallenge.util.PhoneConnectivityUtil_;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PhoneConnectivityUtilTest {
    @Mock
    public Context context;

    @Mock
    public ConnectivityManager connectivityManager;

    @Mock
    public NetworkInfo networkInfo;

    public PhoneConnectivityUtil_ phoneConnectivityUtil;

    @Before
    public void setUp() throws Exception {
        phoneConnectivityUtil = PhoneConnectivityUtil_.getInstance_(context);
    }

    @Test
    public void shouldBeOfflineIfActiveNetworkIsNull() throws Exception {
        // given
        given(context.getSystemService(Context.CONNECTIVITY_SERVICE)).willReturn(
                connectivityManager);
        given(connectivityManager.getActiveNetworkInfo()).willReturn(null);

        // when
        final boolean online = phoneConnectivityUtil.isOnline();

        // then
        assertThat(online).isFalse();
    }

    @Test
    public void shouldBeOnlineIfActiveNetworkIsConnectedOrConnecting() throws Exception {
        // given
        given(context.getSystemService(Context.CONNECTIVITY_SERVICE)).willReturn(
                connectivityManager);
        given(connectivityManager.getActiveNetworkInfo()).willReturn(networkInfo);
        given(networkInfo.isConnectedOrConnecting()).willReturn(true);

        // when
        final boolean online = phoneConnectivityUtil.isOnline();

        // then
        assertThat(online).isTrue();
    }
}
