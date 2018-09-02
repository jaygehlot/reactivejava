import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class RxReader {

    static Observable<String> lines(BufferedReader reader) {
        return Observable.<String>create(subscriber -> {
           String line;

           while((line = reader.readLine()) != null) {
               subscriber.onNext(line);


               if (subscriber.isDisposed()) {
                   break;
               }
           }

            subscriber.onComplete();

           //by default rxjava will run everything on the same thread, unless configured otherwise
           //defining a Scheduler means that this piece of code will
            //run on a different thread, so we are not blocking any computation
        }).subscribeOn(Schedulers.io());
        /**
         * using subscribeOn(Schedulers.io)  means that this code (which is in the return block) is running on a
         * separate thread, which means all the stuff invoked in this code is also going to be running on the same
         * thread
         */
    }

    static Observable<String> lineFromInput() {
        return lines(new BufferedReader(new InputStreamReader(System.in)));
    }
}
