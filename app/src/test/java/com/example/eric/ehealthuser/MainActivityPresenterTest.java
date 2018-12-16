package com.example.eric.ehealthuser;

import android.support.annotation.NonNull;

import com.example.eric.ehealthuser.api.UserService;
import com.example.eric.ehealthuser.entity.Bundle;
import com.example.eric.ehealthuser.model.UhnPatient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MainActivityPresenter.class)
@PowerMockIgnore({"org.apache.http.conn.ssl.*", "javax.net.ssl.*", "javax.crypto.*"})
public class MainActivityPresenterTest {
    MainActivityPresenter presenter = new MainActivityPresenter();
    @Mock
    MainActivityView mockView;
    @Mock
    UserService mockService;
    MainActivityPresenter.TransformBundleToList mockTransfer = presenter.new TransformBundleToList();
    @Mock
    MainActivityPresenter.PatientListSubscriber mockPatientList;
    List<UhnPatient> emptyList = Collections.emptyList();
    // List<UhnPatient> threeContainList = Arrays.asList(new UhnPatient(), new UhnPatient(), new UhnPatient());



    @Before
    public void setUp() throws Exception {

        Whitebox.setInternalState(presenter, "service", mockService);
        RxAndroidPlugins.reset();
        RxJavaPlugins.reset();
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }


    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void displayDataOn() {
        PowerMockito.when(presenter.getPatientList(mockService, mockTransfer)).thenReturn(Observable.just(emptyList));
        presenter.displayDataOn(mockView);
        verify(mockView).displayNoData();


    }

    @Test
    public void getPatientList() {
        TestObserver<List<UhnPatient>> testObserver = new TestObserver<>();
        when(mockService.getUserList()).thenReturn(Observable.just(new Bundle()));
        presenter.getPatientList(mockService, mockTransfer).subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertSubscribed();

    }
}