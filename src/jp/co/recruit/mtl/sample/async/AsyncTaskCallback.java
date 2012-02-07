package jp.co.recruit.mtl.sample.async;

/**
 * AsyncTaskでコールバックさせたいクラスに実装させるインタフェースクラス
 *
 */
public interface AsyncTaskCallback {

	public void onFinishTask(Object result);

}
