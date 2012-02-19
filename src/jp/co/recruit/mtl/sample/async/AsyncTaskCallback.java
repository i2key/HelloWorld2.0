package jp.co.recruit.mtl.sample.async;

import java.util.List;

import jp.co.recruit.mtl.sample.dto.Tweet;

/**
 * AsyncTaskでコールバックさせたいクラスに実装させるインタフェースクラス
 *
 */
public interface AsyncTaskCallback {

	public void onFinishTask(List<Tweet> result);

}
