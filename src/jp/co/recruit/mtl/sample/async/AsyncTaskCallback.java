package jp.co.recruit.mtl.sample.async;

import java.util.List;

import jp.co.recruit.mtl.sample.dto.Tweet;

/**
 * AsyncTask�ŃR�[���o�b�N���������N���X�Ɏ���������C���^�t�F�[�X�N���X
 *
 */
public interface AsyncTaskCallback {

	public void onFinishTask(List<Tweet> result);

}
