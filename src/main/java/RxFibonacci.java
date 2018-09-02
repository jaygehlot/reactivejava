import io.reactivex.Observable;

public class RxFibonacci {

    static Observable<Integer> fibs() {
        return Observable.create(subscriber -> {
            int prev = 0;
            int current = 1;

            subscriber.onNext(0);
            subscriber.onNext(1);

            //while the subscriber is still asking for stuff (i.e. not disposed)
            while (!subscriber.isDisposed()) {
                int oldPrev = current;
                prev = current;
                current += oldPrev;

                //once subscription completes
                subscriber.onNext(current);
            }

        });
    }
}
