package com.example.observables;
//import javax.swing.event.ChangeListener;
//
//import io.reactivex.Observable;
//import io.reactivex.Observer;
//import io.reactivex.disposables.Disposable;
//
//public class HotObservables {
//	Hot<String> hot = new Hot<>();
//}
//
//class Hot<T> extends HotObservables{
//	private ObservableValue<T> observableValue;
//	
//	public Hot() {
//		
//	};
//	public Hot(ObservableValue<T> observableValue) {
//		this.observableValue = observableValue;
//	}
//	
//	private static <T> Observable<T> valuesOf(final ObservableValue<T> fxObservable) {
//			return Observable.create(observableEmitter -> {
//				//emit initial state
//				observableEmitter.onNext(fxObservable.getState());
//				//emit value changes uses a listener
//				ChangeListener listener = (observableValue, prev, current) -> 
//					observableEmitter.onNext(current);
//				fxObservable.addListener(listener);
//			});
//		}
//	}
//}
//
//class ObservableValue<T> implements Observer{
//	private T state;
//	
//	public ObservableValue(){
//		state = null;
//	}
//	
//	public void addListener(ChangeListener listener) {
//		this.addListener(listener);
//	}
//
//	public void setState(T val) {
//		state = val;
//	}
//	
//	public T getState() {
//		return state;
//	}
//
//	@Override
//	public void onSubscribe(Disposable d) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onNext(Object t) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onError(Throwable e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void onComplete() {
//		// TODO Auto-generated method stub
//		
//	}
//}
