package com.pan.rxjavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.joins.Pattern;
import rx.joins.Pattern2;
import rx.joins.Plan0;
import rx.observables.GroupedObservable;
import rx.observables.JoinObservable;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();

    private TextView mView;

    private List<String> mData = new ArrayList<>();

    private List<CommunityBean> mCommunityBeanList = new ArrayList<>();

    private List<HouseBean> mHouseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Main thread : " + Thread.currentThread().getId());
        mCommunityBeanList = Data.getData();
        mHouseList = Data.getHouseList();

        init();
        create();
//        combinateOperator();
//        transformate();
//        filter();
//        groupBy();
//        scan();
//        getEveryCommunityHouseName();
//        getEveryCommunityName();
//        initData();
//        observable();
    }

    private void init() {
        mView = (TextView) findViewById(R.id.tv_text);
    }

    private void initData() {
        mData.add("RxJava");
        mData.add("RxAndroid");
        mData.add("Retrofit");
        mData.add("Realm");
        mData.add("Picasso");
        mData.add("Glide");
    }

    //create Observable ways
    private void observable() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.d(TAG, "Call : " + Thread.currentThread().getId());
                subscriber.onNext("Hello, RxJava !");
                subscriber.onCompleted();
            }
        });

        Observable<String> from = Observable.from(mData);

        Observable<List<String>> just = Observable.just(mData);

        //create observer ways
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "Complete : " + Thread.currentThread().getId());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "Error : " + Thread.currentThread().getId());
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "Next : " + Thread.currentThread().getId());
                mView.setText(mView.getText() + s + "\n");
            }
        };

        Subscriber<List<String>> subscriber = new Subscriber<List<String>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "Complete : " + Thread.currentThread().getId());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "Error : " + Thread.currentThread().getId());
            }

            @Override
            public void onNext(List<String> s) {
                Log.d(TAG, "Next : " + Thread.currentThread().getId());
                for (String string : s) {
                    mView.setText(mView.getText() + string + "\n");
                }
            }
        };

//        PublishSubject<String> publish = PublishSubject.create();
//        publish.subscribe(observer);
//        publish.onNext("RxJava");
//        publish.onNext("RxAndroid");
//        publish.onNext("Retrofit");

//        BehaviorSubject<String> behavior = BehaviorSubject.create();
//        behavior.onNext("RxJava");
//        behavior.onNext("RxAndroid");
//        behavior.subscribe(observer);
//        behavior.onNext("Retrofit");

//        ReplaySubject<String> replay = ReplaySubject.create();
//        replay.onNext("RxJava");
//        replay.onNext("RxAndroid");
//        replay.onNext("Retrofit");
//        replay.onCompleted();
//        replay.subscribe(observer);

        AsyncSubject async = AsyncSubject.create();
        async.subscribe(observer);
        async.onNext("RxJava");
        async.onNext("RxAndroid");
        async.onNext("Retrofit");
        async.onCompleted();

        just.subscribe(subscriber);
    }

    private void scan() {
        Observable.just(1, 2, 3, 4, 5)
                .scan(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer, Integer integer2) {
                        return integer * integer2;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        mView.setText(mView.getText() + integer.toString() + "\n");
                    }
                });
    }

    /**
     * 创建操作
     */
    private void create() {
        Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        for (int i = 0; i < 5; i++) {
                            subscriber.onNext(i);
                        }
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        })
        .subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                mView.setText("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                mView.setText(e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                mView.setText(mView.getText() + integer.toString() + "\n");
            }
        });

    }

    //过滤操作符
    private void filter() {
        //过滤我们不想要的值
        Observable.from(mCommunityBeanList)
                .filter(new Func1<CommunityBean, Boolean>() {
                    @Override
                    public Boolean call(CommunityBean communityBean) {
                        return communityBean.getHouseBeanList().size() > 5;
                    }
                })
                .subscribe(new Action1<CommunityBean>() {
                    @Override
                    public void call(CommunityBean communityBean) {
                        mView.setText("Community : " + communityBean.getName());
                    }
                });

//        获取原始数列发射前n个元素
        Observable.from(mCommunityBeanList.get(0).getHouseBeanList())
                .take(3)
                .subscribe(new Action1<HouseBean>() {
                    @Override
                    public void call(HouseBean houseBean) {
                        mView.setText(mView.getText() + houseBean.getName() + "\n");
                    }
                });

//        获取原始数列发射后n个元素
        Observable.from(mCommunityBeanList.get(0).getHouseBeanList())
                .takeLast(3)
                .subscribe(new Action1<HouseBean>() {
                    @Override
                    public void call(HouseBean houseBean) {
                        mView.setText(mView.getText() + houseBean.getName() + "\n");
                    }
                });

        Observable<Long> observableA = Observable.interval(300, TimeUnit.MILLISECONDS);
        Observable<Long> observableB = Observable.interval(800, TimeUnit.MILLISECONDS);

        observableA.takeUntil(observableB)
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        System.exit(0);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        mView.setText(aLong.toString());

                    }
                });

        /**
         * 过滤发射速度太快数据，如果在指定时间间隔未发射数据，则发射最后一个；
         * 默认是在computation调度器上执行的，也可以指定调度器；
         */
        Observable.from(mCommunityBeanList)
                .debounce(2, TimeUnit.SECONDS)
                .subscribe(new Action1<CommunityBean>() {
                    @Override
                    public void call(CommunityBean communityBean) {
                        mView.setText(mView.getText() + communityBean.getName() + "\n");
                    }
                });

        /**
         * distinct : 过滤重复数据，默认不在任何特定调度器执行
         * distinctUntilChanged : 判断当前数据与前一个数据是否相同，默认不在任何特定调度器执行
         */
        Observable.just(1, 2, 3, 3, 3, 4, 5, 2, 6)
                .distinct()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        mView.setText(mView.getText() + integer.toString() + "\n");
                    }
                });

        Observable.just(1, 2, 3, 3, 4, 5, 3, 4, 2)
                .distinct(new Func1<Integer, Object>() {
                    @Override
                    public Object call(Integer integer) {
                        return 3;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        mView.setText(mView.getText() + integer.toString() + "\n");
                    }
                });

        Observable.just(1, 2, 3, 3, 3, 2, 2, 4)
                .distinctUntilChanged()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        mView.setText(mView.getText() + integer.toString() + "\n");
                    }
                });

        Observable.just(1, 2, 3, 3, 3, 2, 2, 4)
                .distinctUntilChanged(new Func1<Integer, Object>() {
                    @Override
                    public Object call(Integer integer) {
                        Log.d(TAG, integer.toString());
                        return 3;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "Call : " + integer);
                        mView.setText(mView.getText() + integer.toString() + "\n");
                    }
                });


        Observable.just(1, 2, 3, 4)
                .distinctUntilChanged(new Func2<Integer, Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer, Integer integer2) {
                        Log.d(TAG, "Integer One : " + integer);
                        Log.d(TAG, "Integer Two : " + integer2);
                        return integer < integer2;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d("Call : ", integer.toString());
                        mView.setText(mView.getText() + integer.toString() + "\n");
                    }
                });
        /**
         * 默认是不在任何特定调度器执行的
         * elementAt : 发射第N项数据，通过索引获取到值，若索引为负数或者大于原始数据项的会抛出异常
         * elementAtOrDefault : 发射第N项数据，通过索引获取到值，若索引为负数，则抛出异常；若大于原始数据项，则会发射一个默认值，通过参数指定
         * ignoreElements : 不发射任何数据，只发射Observable的终止通知（onCompleted or onError）
         */
        Observable.from(mCommunityBeanList.get(0).getHouseBeanList())
                .elementAt(3)
                .subscribe(new Action1<HouseBean>() {
                    @Override
                    public void call(HouseBean houseBean) {
                        mView.setText(houseBean.getName());
                    }
                });

        try {
            Observable.from(mCommunityBeanList.get(1).getHouseBeanList())
                    .elementAtOrDefault(4, mCommunityBeanList.get(1).getHouseBeanList().get(0))
                    .subscribe(new Action1<HouseBean>() {
                        @Override
                        public void call(HouseBean houseBean) {
                            mView.setText(houseBean.getName());
                        }
                    });
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        Observable.from(mCommunityBeanList.get(0).getHouseBeanList())
                .ignoreElements()
                .subscribe(new Action1<HouseBean>() {
                    @Override
                    public void call(HouseBean houseBean) {
                        mView.setText(houseBean.getName());
                    }
                });
        /**
         * 默认不在任何特定调度器执行
         * first : 只发射第一项（或者满足某个条件的第一项）,若没有满足条件的话会抛出异常
         * firstOrDefault : 只发射第一项（或者满足某个条件的第一项）,若没发射数据时，会发射默认值，通过参数传递
         * takeFirst : 只发射第一项（或者满足某个条件的第一项）,若没有满足条件会返回空的Observable，不会调用onNext,但会调用onCompleted
         * single : 与first类似，但是如果产生的结果满足指定条件的数量不为1，就会抛出NoSuchElementException(以错误通知终止)
         * singleOrDefault : 如果原始数据发射超过一个数据，会以错误通知终止
         */
        try {
            Observable.from(mCommunityBeanList.get(0).getHouseBeanList())
                    .first()
                    .subscribe(new Action1<HouseBean>() {
                        @Override
                        public void call(HouseBean houseBean) {
                            mView.setText(houseBean.getName());
                        }
                    });
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        try {
            Observable.from(mCommunityBeanList.get(1).getHouseBeanList())
                    .first(new Func1<HouseBean, Boolean>() {
                        @Override
                        public Boolean call(HouseBean houseBean) {
                            return true;
                        }
                    })
                    .subscribe(new Action1<HouseBean>() {
                        @Override
                        public void call(HouseBean houseBean) {
                            mView.setText(houseBean.getName());
                        }
                    });
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        Observable.from(mCommunityBeanList.get(1).getHouseBeanList())
                .firstOrDefault(mCommunityBeanList.get(1).getHouseBeanList().get(3))
                .subscribe(new Action1<HouseBean>() {
                    @Override
                    public void call(HouseBean houseBean) {
                        mView.setText(houseBean.getName());
                    }
                });

        Observable.from(mCommunityBeanList.get(0).getHouseBeanList())
                .firstOrDefault(mCommunityBeanList.get(0).getHouseBeanList().get(3), new Func1<HouseBean, Boolean>() {
                    @Override
                    public Boolean call(HouseBean houseBean) {
                        return true;
                    }
                })
                .subscribe(new Action1<HouseBean>() {
                    @Override
                    public void call(HouseBean houseBean) {
                        mView.setText(houseBean.getName());
                    }
                });

        Observable.from(mCommunityBeanList)
                .takeFirst(new Func1<CommunityBean, Boolean>() {
                    @Override
                    public Boolean call(CommunityBean communityBean) {
                        return false;
                    }
                })
                .subscribe(new Action1<CommunityBean>() {
                    @Override
                    public void call(CommunityBean communityBean) {
                        mView.setText(communityBean.getName());
                    }
                });

        Observable.from(mCommunityBeanList)
                .single()
                .subscribe(new Subscriber<CommunityBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(CommunityBean communityBean) {
                        mView.setText(communityBean.getName());
                    }
                });

        Observable.from(mCommunityBeanList)
                .single(new Func1<CommunityBean, Boolean>() {
                    @Override
                    public Boolean call(CommunityBean communityBean) {
                        return communityBean.getName().equals("Community One");
                    }
                })
                .subscribe(new Subscriber<CommunityBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(CommunityBean communityBean) {
                        mView.setText(communityBean.getName());
                    }
                });

        Observable.from(mCommunityBeanList)
                .singleOrDefault(mCommunityBeanList.get(0))
                .subscribe(new Subscriber<CommunityBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(CommunityBean communityBean) {
                        mView.setText(communityBean.getName());
                    }
                });

        Observable.from(mCommunityBeanList)
                .singleOrDefault(mCommunityBeanList.get(0), new Func1<CommunityBean, Boolean>() {
                    @Override
                    public Boolean call(CommunityBean communityBean) {
                        return communityBean.getName().equals("Community One");
                    }
                })
                .subscribe(new Subscriber<CommunityBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(CommunityBean communityBean) {
                        mView.setText(communityBean.getName());
                    }
                });

        /**
         * last : 只发射最后一项（或者满足某个条件的最后一项）
         * lastOrDefault : 与last类似
         */
        Observable.from(mCommunityBeanList)
                .last()
                .subscribe(new Action1<CommunityBean>() {
                    @Override
                    public void call(CommunityBean communityBean) {
                        mView.setText(communityBean.getName());
                    }
                });

        Observable.from(mCommunityBeanList)
                .last(new Func1<CommunityBean, Boolean>() {
                    @Override
                    public Boolean call(CommunityBean communityBean) {
                        return communityBean.getName().equals("Community One");
                    }
                })
                .subscribe(new Subscriber<CommunityBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(CommunityBean communityBean) {
                        mView.setText(communityBean.getName());
                    }
                });

        Observable.from(mCommunityBeanList)
                .lastOrDefault(mCommunityBeanList.get(1))
                .subscribe(new Action1<CommunityBean>() {
                    @Override
                    public void call(CommunityBean communityBean) {
                        mView.setText(communityBean.getName());
                    }
                });

        Observable.from(mCommunityBeanList)
                .lastOrDefault(mCommunityBeanList.get(1), new Func1<CommunityBean, Boolean>() {
                    @Override
                    public Boolean call(CommunityBean communityBean) {
                        return false;
                    }
                })
                .subscribe(new Subscriber<CommunityBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(CommunityBean communityBean) {
                        mView.setText(communityBean.getName());
                    }
                });

        /**
         * skip : 抑制Observable发射的前N项数据，默认不在任何特定的调度器上执行;变体默认是在computation调度器上执行
         */
        Observable.from(mCommunityBeanList.get(0).getHouseBeanList())
                .skip(2)
                .subscribe(new Action1<HouseBean>() {
                    @Override
                    public void call(HouseBean houseBean) {
                        mView.setText(mView.getText() + houseBean.getName() + "\n");
                    }
                });

        Observable.from(mCommunityBeanList.get(1).getHouseBeanList())
                .skip(1, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<HouseBean>() {
                    @Override
                    public void call(HouseBean houseBean) {
                        mView.setText(mView.getText() + houseBean.getName() + "\n");
                    }
                });

        /**
         * skipLast : 抑制Observable发射的后N项数据,默认不在任何特定调度器上执行
         */
        Observable.from(mCommunityBeanList.get(0).getHouseBeanList())
                .skipLast(2)
                .subscribe(new Action1<HouseBean>() {
                    @Override
                    public void call(HouseBean houseBean) {
                        mView.setText(mView.getText() + houseBean.getName() + "\n");
                    }
                });

        Observable.from(mCommunityBeanList.get(1).getHouseBeanList())
                .skipLast(1, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<HouseBean>() {
                    @Override
                    public void call(HouseBean houseBean) {
                        mView.setText(mView.getText() + houseBean.getName() + "\n");
                    }
                });

        /**
         * take : 只发射前面的N项数据，发射完成会调用onCompleted;如果N大于Observable发射的数据项，不会抛异常
         *        或者发射onError，会发射已有的数据。默认不在任何特定调度器上执行
         */
        Observable.from(mCommunityBeanList.get(1).getHouseBeanList())
                .take(8)
                .subscribe(new Subscriber<HouseBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(HouseBean houseBean) {
                        mView.setText(mView.getText() + houseBean.getName() + "\n");
                    }
                });

        Observable.from(mCommunityBeanList.get(1).getHouseBeanList())
                .take(1, TimeUnit.SECONDS)
                .subscribe(new Subscriber<HouseBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(HouseBean houseBean) {
                        mView.setText(mView.getText() + houseBean.getName() + "\n");
                    }
                });

        /**
         * takeLast : 发射Observable发射的最后N项数据，默认不在任何特定调度器上执行;变体发射在原始Observable的
         *            生命周期内的最后一段时间内,默认是在computation调度器上执行，也可以自己指定
         * takeLastBuffer : 与takeLast类似，唯一不同的是它把所有的数据项收集到一个List再发射，而不是一次发射一个
         */
        Observable.from(mCommunityBeanList.get(0).getHouseBeanList())
                .takeLast(3)
                .subscribe(new Action1<HouseBean>() {
                    @Override
                    public void call(HouseBean houseBean) {
                        mView.setText(mView.getText() + houseBean.getName() + "\n");
                    }
                });

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        Observable.from(list)
                .takeLast(1, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        mView.setText(mView.getText() + integer.toString() + "\n");
                    }
                });

        Observable.from(mCommunityBeanList.get(0).getHouseBeanList())
                .takeLastBuffer(4)
                .flatMap(new Func1<List<HouseBean>, Observable<HouseBean>>() {
                    @Override
                    public Observable<HouseBean> call(List<HouseBean> houseBeen) {
                        return Observable.from(houseBeen);
                    }
                })
                .subscribe(new Action1<HouseBean>() {
                    @Override
                    public void call(HouseBean houseBean) {
                        mView.setText(mView.getText() + houseBean.getName() + "\n");
                    }
                });
    }

    /**
     * 变换操作
     */
    private void transformate() {
        /**
         * map : 对Observable发射的每一项数据应用于一个函数，然后执行变换操作
         * cast : 将原始Observable发射的每一项数据都强制转换为一个指定的类型，然后再发射数据
         * flatMap
         * flatMapIterable : 生成Iterable
         * concatMap : 按次序连接而不是合并那些生成Observables，可以解决交叉问题
         * switchMap : 当原始Observable发射一个新的数据（Observable）时，它将取消订阅并停止监视产生之前那个数据的
         *             Observable，只监视当前这一个
         * scan : 连续地对数据的每一项应用一个函数，然后连续发射结果，类似累加器
         * groupBy : 将一个Observable分拆为一些Observable集合，它们中的每一个发射原始Observable的一个子序列。
         *           类似SQL groupBy
         */
        Observable.from(mCommunityBeanList)
                .map(new Func1<CommunityBean, String>() {
                    @Override
                    public String call(CommunityBean communityBean) {
                        return communityBean.getName();
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        mView.setText(mView.getText() + s + "\n");
                    }
                });

        Observable.from(mCommunityBeanList)
                .cast(CommunityBean.class)
                .subscribe(new Action1<CommunityBean>() {
                    @Override
                    public void call(CommunityBean bean) {
                        mView.setText(mView.getText() + bean.getName() + "\n");
                    }
                });

        Observable.from(mCommunityBeanList)
               .flatMap(new Func1<CommunityBean, Observable<HouseBean>>() {
                   @Override
                   public Observable<HouseBean> call(CommunityBean communityBean) {
                       return Observable.from(communityBean.getHouseBeanList());
                   }
               })
                .subscribe(new Action1<HouseBean>() {
                    @Override
                    public void call(HouseBean houseBean) {
                        mView.setText(mView.getText() + houseBean.getName() + "\n");
                    }
                });

        Observable.from(mCommunityBeanList)
                .flatMap(new Func1<CommunityBean, Observable<HouseBean>>() {
                    @Override
                    public Observable<HouseBean> call(CommunityBean communityBean) {
                        return Observable.from(communityBean.getHouseBeanList());
                    }
                }, 9)
                .subscribe(new Subscriber<HouseBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(HouseBean houseBean) {
                        mView.setText(mView.getText() + houseBean.getName() + "\n");
                    }
                });

        Observable.from(mCommunityBeanList)
                .flatMapIterable(new Func1<CommunityBean, Iterable<HouseBean>>() {
                    @Override
                    public Iterable<HouseBean> call(CommunityBean communityBean) {
                        return communityBean.getHouseBeanList();
                    }
                })
                .subscribe(new Action1<HouseBean>() {
                    @Override
                    public void call(HouseBean houseBean) {
                        mView.setText(mView.getText() + houseBean.getName() + "\n");
                    }
                });

        Observable.from(mCommunityBeanList)
                .concatMap(new Func1<CommunityBean, Observable<HouseBean>>() {
                    @Override
                    public Observable<HouseBean> call(CommunityBean communityBean) {
                        return Observable.from(communityBean.getHouseBeanList());
                    }
                })
                .subscribe(new Action1<HouseBean>() {
                    @Override
                    public void call(HouseBean houseBean) {
                        mView.setText(mView.getText() + houseBean.getName() + "\n");
                    }
                });

        Observable.just(2, 3, 3, 3, 4, 5)
                .scan(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer, Integer integer2) {
                        Log.d(TAG, "One : " + integer);
                        Log.d(TAG, "Two : " + integer2);
                        return integer + integer2;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        mView.setText(mView.getText() + integer.toString() + "\n");
                    }
                });

        Observable<GroupedObservable<String, HouseBean>> observable = Observable.from(mHouseList)
                .groupBy(new Func1<HouseBean, String>() {
                    @Override
                    public String call(HouseBean houseBean) {
                        return houseBean.getCommunityName();
                    }
                });

        Observable.concat(observable)
                .subscribe(new Action1<HouseBean>() {
                    @Override
                    public void call(HouseBean houseBean) {
                        mView.setText(mView.getText() + "小区名： " + houseBean.getCommunityName() +
                                " 房名：" + houseBean.toString() + "\n");
                    }
                });

        /**
         * buffer : 定期收集Observable的数据放进一个数据包裹（以列表的形式），然后发射这些包裹，而不是一次发射一个值
         *          count : 列表大小
         *          skip : 每次发射后扔掉数据的个数(每次缓存count个，扔掉skip个；再缓存count个，扔掉skip个；如此反复)
         */
        Observable.from(mCommunityBeanList.get(1).getHouseBeanList())
                .buffer(4)
                .flatMap(new Func1<List<HouseBean>, Observable<HouseBean>>() {
                    @Override
                    public Observable<HouseBean> call(List<HouseBean> houseBeen) {
                        Log.d(TAG, "Length : " + houseBeen.size());
                        return Observable.from(houseBeen);
                    }
                })
                .subscribe(new Subscriber<HouseBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(HouseBean houseBean) {
                        mView.setText(mView.getText() + houseBean.getName() + "\n");
                    }
                });

        Observable.from(mCommunityBeanList.get(1).getHouseBeanList())
                .buffer(2, 2)
                .flatMap(new Func1<List<HouseBean>, Observable<HouseBean>>() {
                    @Override
                    public Observable<HouseBean> call(List<HouseBean> houseBeen) {
                        return Observable.from(houseBeen);
                    }
                })
                .subscribe(new Subscriber<HouseBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(HouseBean houseBean) {
                        mView.setText(mView.getText() + houseBean.getName() + "\n");
                    }
                });

        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .buffer(4, 3)
                .subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> integers) {
                        mView.setText(mView.getText() + integers.toString() + "\n");
                    }
                });
    }

    /**
     * 组合操作符
     */
    private void combinateOperator() {
        /**
         * And/Then/When : 操作符组合的行为类似于zip，但是它们使用一个中间数据结构。接受两个或多个Observable，
         *                 一次一个将它们的发射物合并到Pattern对象，然后操作那个Pattern对象，变换为一个Plan。
         *                 随后将这些Plan变换为Observable的发射物
         */
        Observable<HouseBean> houseList = Observable.from(mCommunityBeanList.get(1).getHouseBeanList());
        Observable<Long> time = Observable.interval(1, TimeUnit.SECONDS);

        Pattern2<HouseBean, Long> pattern = JoinObservable.from(houseList).and(time);
        Plan0<HouseBean> plan = pattern.then(new Func2<HouseBean, Long, HouseBean>() {
            @Override
            public HouseBean call(HouseBean houseBean, Long aLong) {
                return houseBean;
            }
        });

        JoinObservable
                .when(plan)
                .toObservable()
                .subscribe(new Subscriber<HouseBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(HouseBean houseBean) {
                        mView.setText(mView.getText() + houseBean.getName() + "\n");
                    }
                });
    }
}
