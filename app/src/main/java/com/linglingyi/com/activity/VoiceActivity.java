package com.linglingyi.com.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.iflytek.sunflower.FlowerCollector;
import com.linglingyi.com.adapter.VoiceAdapter;
import com.linglingyi.com.base.BaseActivity;
import com.linglingyi.com.entity.VoiceEntity;
import com.linglingyi.com.event.PlanCloseEvent;
import com.linglingyi.com.model.Area;
import com.linglingyi.com.model.BaseEntity;
import com.linglingyi.com.model.BindCard;
import com.linglingyi.com.model.IndustryModel;
import com.linglingyi.com.model.PreviewPlanModel;
import com.linglingyi.com.model.ProvinceModel;
import com.linglingyi.com.utils.CommonUtils;
import com.linglingyi.com.utils.Constant;
import com.linglingyi.com.utils.CustomAnimation;
import com.linglingyi.com.utils.DateUtil;
import com.linglingyi.com.utils.FucUtil;
import com.linglingyi.com.utils.JsonParser;
import com.linglingyi.com.utils.LogUtils;
import com.linglingyi.com.utils.StringUtil;
import com.linglingyi.com.utils.Utils;
import com.linglingyi.com.utils.ViewUtils;
import com.linglingyi.com.utils.okgo.OkClient;
import com.linglingyi.com.viewone.UMExpandLayout;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.wuyouchuangke.com.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import butterknife.OnTouch;

/**
 * @author dyx
 * @date on 2019/4/22
 * @describe
 */
public class VoiceActivity extends BaseActivity {
    private static String TAG = VoiceActivity.class.getSimpleName();
    private static Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*");
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.imageView1)
    ImageView imageView1;
    @BindView(R.id.iat_recognize)
    Button iatRecognize;
    @BindView(R.id.iat_stop)
    Button iatStop;
    @BindView(R.id.iat_cancel)
    Button iatCancel;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.ll_top)
    UMExpandLayout topBar;
    @BindView(R.id.tv_notice)
    TextView tvNotice;
    @BindView(R.id.ll_record)
    View llRecord;
    @BindView(R.id.iv_volume)
    ImageView mVolumeImg;
    int ret = 0; // 函数调用返回值
    @BindView(R.id.imgView)
    ImageView imgView;
    // 语音听写对象
    private SpeechRecognizer mIat;
    // 语音听写UI
    private RecognizerDialog mIatDialog;
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();
    private List<VoiceEntity> myList;
    private VoiceAdapter voiceAdapter;
    private Toast mToast;
    private SharedPreferences mSharedPreferences;
    // 引擎类型
    private String mEngineType = SpeechConstant.TYPE_CLOUD;
    private boolean mTranslateEnable = false;
    private String resultType = "json";
    private boolean cyclic = false;//音频流识别是否循环调用
    private StringBuffer buffer = new StringBuffer();
//    private String NOTICE = "HI,我是您的智能代还助理，将帮助您规划预留还款计划，请告诉我您要的计划安排。请按照以下文字进行语音输入：\n金额：2000\n开始时间：2019年4月18\n结束时间：2019年4月25\n地区：浙江省台州市";
    private String TOP_NOTICE = "请按照以下文字模板进行语音输入：\n金额：2000\n开始时间：2019年X月X日\n结束时间：2019年X月X日\n地区：浙江省台州市\n注意：\n\t\t如需修改内容，只需再次语音输入覆盖原先内容即可\n\t\t地区若是直辖市请输入如：地区北京市北京市\n\t\t开始时间必须在当前时间后一天";
    private BindCard mBindCard;
    private boolean isRecord = false;
    private PreviewPlanModel previewPlanModel = new PreviewPlanModel();
    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            Log.d(TAG, "SpeechRecognizer init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败，错误码：" + code);
            }
        }
    };
    private String[] strs = new String[]{
            "金额",
            "开始时间",
            "结束时间",
            "地区",
    };
    /**
     * 听写UI监听器
     */
    private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            if (mTranslateEnable) {
                printTransResult(results);
            } else {
                printResult(results);
            }
        }

        /**
         * 识别回调错误.
         */
        @Override
        public void onError(SpeechError error) {
            if (mTranslateEnable && error.getErrorCode() == 14002) {
                showTip(error.getPlainDescription(true) + "\n请确认是否已开通翻译功能");
            } else {
                showTip(error.getPlainDescription(true));
            }
        }

    };
    private int[] volumeImg = new int[]{
            R.drawable.p1,
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4,
            R.drawable.p5,
            R.drawable.p6,
            R.drawable.p7,
    };
    /**
     * 听写监听器。
     */
    private RecognizerListener mRecognizerListener = new RecognizerListener() {

        @Override
        public void onVolumeChanged(int volume, byte[] data) {

//            volume
            if (volume < 0 || volume / 3 > volumeImg.length - 1) {
                mVolumeImg.setImageResource(volumeImg[volumeImg.length - 1]);
            } else {
                mVolumeImg.setImageResource(volumeImg[volume / 3]);
            }
            Log.d(TAG, "返回音频数据：" + data.length);
        }

        @Override
        public void onBeginOfSpeech() {
            // 此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入
//            showTip("开始说话");
        }

        @Override
        public void onEndOfSpeech() {
            // 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
            setStopStatus();
        }

        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            Log.d(TAG, results.getResultString());
            if ("json".equals(resultType)) {
                if (mTranslateEnable) {
                    printTransResult(results);
                } else {
                    printResult(results);
                }
            }
//            else if (resultType.equals("plain")) {
//                buffer.append(results.getResultString());
//                mResultText.setText(buffer.toString());
//                mResultText.setSelection(mResultText.length());
//            }

            if (isLast & cyclic) {
                // TODO 最后的结果
                Message message = Message.obtain();
                message.what = 0x001;
                han.sendMessageDelayed(message, 100);
            }
        }

        @Override
        public void onError(final SpeechError error) {
            // Tips：
            // 错误码：10118(您没有说话)，可能是录音机权限被禁，需要提示用户打开应用的录音权限。
            if (mTranslateEnable && error.getErrorCode() == 14002) {
                showTip(error.getPlainDescription(true) + "\n请确认是否已开通翻译功能");
            } else {
                showTip(error.getPlainDescription(true));
                setStopStatus();
            }
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //		Log.d(TAG, "session id =" + sid);
            //	}
        }
    };
    Handler han = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x001) {
                executeStream();
            }
        }
    };

    @OnTouch(R.id.imgView)
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_UP:
                if (isRecord) {
                    mIat.stopListening();
                    setStopStatus();
                }

                break;
        }
        return false;
    }

    private void setStopStatus() {
        llRecord.setVisibility(View.GONE);
        isRecord = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (null != mIat) {
            // 退出时释放连接
            mIat.cancel();
            mIat.destroy();
        }
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int initLayout() {
        return R.layout.act_voice;
    }

    @Override
    public void initData() {
        tvTitle.setText("制定计划");
        mBindCard = (BindCard) getIntent().getSerializableExtra("bindCard");
        // 初始化识别无UI识别对象
        // 使用SpeechRecognizer对象，可根据回调消息自定义界面；
        mIat = SpeechRecognizer.createRecognizer(context, mInitListener);
        // 初始化听写Dialog，如果只使用有UI听写功能，无需创建SpeechRecognizer
        // 使用UI听写功能，请根据sdk文件目录下的notice.txt,放置布局文件和图片资源
        mIatDialog = new RecognizerDialog(context, mInitListener);

        mSharedPreferences = getSharedPreferences("com.iflytek.setting",
                Activity.MODE_PRIVATE);
        mToast = Toast.makeText(this, "", Toast.LENGTH_LONG);
        initRecycler();
        tvNotice.setText(voiceAdapter.getSpanString(TOP_NOTICE));
    }

    private void initRecycler() {
        myList = new ArrayList<VoiceEntity>(
        ) {{
            add(new VoiceEntity(VoiceEntity.LEFT_TYPE_NOTICE, Constant.VOICE_NOTICE));
            add(new VoiceEntity().setItemType(VoiceEntity.LEFT_TYPE_NOTICE).setInputContent("请输入还款金额，例：\n金额2000"));
            add(new VoiceEntity().setItemType(VoiceEntity.LEFT_YTPE_CONTENT));
//            add(new VoiceEntity(VoiceEntity.RIGHT_TYPE, "模板：金额2000"));
//            add(new VoiceEntity(VoiceEntity.RIGHT_TYPE, "模板：2019年4月18号到2019年4月25"));
//            add(new VoiceEntity(VoiceEntity.RIGHT_TYPE, "模板：地区浙江省台州市"));
//            add(new VoiceEntity(VoiceEntity.LEFT_YTPE_CONTENT, ""));
        }};
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rvList.setLayoutManager(linearLayoutManager);
        voiceAdapter = new VoiceAdapter(myList, mBindCard);
        voiceAdapter.openLoadAnimation();
        voiceAdapter.isFirstOnly(false);
        voiceAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        voiceAdapter.setMerchanNo(getMerNo());
//        voiceAdapter.isFirstOnly(true);
        previewPlanModel.setIsLuodi("1");
        previewPlanModel.setIsZiXuan("1");
        previewPlanModel.setZhia(false);
        voiceAdapter.setPreviewPlanModel(previewPlanModel);
        voiceAdapter.openLoadAnimation(new CustomAnimation());
        voiceAdapter.bindToRecyclerView(rvList);

        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int itemHeight = 0;
                View child = null;
                RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();//获取LayoutManager

                if (manager != null && manager instanceof LinearLayoutManager) {
                    //第一个可见的位置
                    child = manager.findViewByPosition(0);
                    if (child != null) {
                        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                        itemHeight = child.getHeight() + params.topMargin + params.bottomMargin;
                    }
                    if (recyclerView.computeVerticalScrollOffset() > itemHeight / 2) {
                        if (!topBar.isShow()) {
                            topBar.expand();
                        }
                    } else {
                        if (topBar.isShow()) {
                            topBar.collapse();
                        }
                    }
                }
            }
        });
    }

    @OnLongClick(R.id.imgView)
    public boolean longClick() {
        setStartStatus();
        startVoice();
        return false;
    }

    private void setStartStatus() {
        isRecord = true;
        llRecord.setVisibility(View.VISIBLE);
    }

    // 开始听写
    // 如何判断一次听写结束：OnResult isLast=true 或者 onError
    private void startVoice() {
        // 移动数据分析，收集开始听写事件
        FlowerCollector.onEvent(context, "iat_recognize");

        buffer.setLength(0);
        mIatResults.clear();
        // 设置参数
        setParam();
        boolean isShowDialog = mSharedPreferences.getBoolean(
                getString(R.string.pref_key_iat_show), true);
        if (false) {
            // 显示听写对话框
            mIatDialog.setListener(mRecognizerDialogListener);
            mIatDialog.show();
            TextView txt = (TextView) mIatDialog.getWindow().getDecorView().findViewWithTag("textlink");
            txt.setText("");
            showTip(getString(R.string.text_begin));
        } else {
            // 不显示听写对话框
            ret = mIat.startListening(mRecognizerListener);
            if (ret != ErrorCode.SUCCESS) {
                showTip("听写失败" + ret);
                LogUtils.e("错误码：" + ret);
            } else {
                setStartStatus();
//                showTip(getString(R.string.text_begin));
            }
        }
    }

    private void showTip(final String str) {
        ViewUtils.makeToast(context, str, 2000);
//        mToast.setText(str);
//        mToast.show();
    }

    /**
     * 参数设置
     *
     * @return
     */
    public void setParam() {
        // 清空参数
        mIat.setParameter(SpeechConstant.PARAMS, null);

        // 设置听写引擎
        mIat.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);
        // 设置返回结果格式
        mIat.setParameter(SpeechConstant.RESULT_TYPE, resultType);

        this.mTranslateEnable = mSharedPreferences.getBoolean(this.getString(R.string.pref_key_translate), false);
        if (mTranslateEnable) {
            Log.i(TAG, "translate enable");
            mIat.setParameter(SpeechConstant.ASR_SCH, "1");
            mIat.setParameter(SpeechConstant.ADD_CAP, "translate");
            mIat.setParameter(SpeechConstant.TRS_SRC, "its");
        }

        String lag = mSharedPreferences.getString("iat_language_preference",
                "mandarin");
        if ("en_us".equals(lag)) {
            // 设置语言
            mIat.setParameter(SpeechConstant.LANGUAGE, "en_us");
            mIat.setParameter(SpeechConstant.ACCENT, null);

            if (mTranslateEnable) {
                mIat.setParameter(SpeechConstant.ORI_LANG, "en");
                mIat.setParameter(SpeechConstant.TRANS_LANG, "cn");
            }
        } else {
            // 设置语言
            mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
            // 设置语言区域
            mIat.setParameter(SpeechConstant.ACCENT, lag);

            if (mTranslateEnable) {
                mIat.setParameter(SpeechConstant.ORI_LANG, "cn");
                mIat.setParameter(SpeechConstant.TRANS_LANG, "en");
            }
        }
        //此处用于设置dialog中不显示错误码信息
        //mIat.setParameter("view_tips_plain","false");

        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mIat.setParameter(SpeechConstant.VAD_BOS, "4000");
//        mSharedPreferences.getString("iat_vadbos_preference", "4000")
        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mIat.setParameter(SpeechConstant.VAD_EOS, "5000");
//        mSharedPreferences.getString("iat_vadeos_preference", "1000")
        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mIat.setParameter(SpeechConstant.ASR_PTT, mSharedPreferences.getString("iat_punc_preference", "1"));

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        mIat.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/iat.wav");
    }

    @OnClick({R.id.iat_recognize, R.id.iat_stop, R.id.iat_cancel, R.id.iv_back})
    public void onClick(View view) {
        if (null == mIat) {
            // 创建单例失败，与 21001 错误为同样原因，参考 http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=9688
            this.showTip("创建对象失败，请确认 libmsc.so 放置正确，且有调用 createUtility 进行初始化");
            return;
        }
        switch (view.getId()) {
            case R.id.imgView:
                startVoice();
                break;
            // 停止听写
            case R.id.iat_stop:
                mIat.stopListening();
                showTip("停止听写");
                break;
            // 取消听写
            case R.id.iat_cancel:
                mIat.cancel();
                showTip("取消听写");
                break;
            case R.id.iv_back:
                ViewUtils.overridePendingTransitionBack(context);
                break;
            default:
                break;
        }
    }

    private void printResult(RecognizerResult results) {
        setStopStatus();

        String text = JsonParser.parseIatResult(results.getResultString());

        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mIatResults.put(sn, text);

        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }

        dealContent(resultBuffer.toString());
    }

    /**
     * @param content 识别内容
     *                处理逻辑：
     *                1.一次语音识别会回调两次结果，不知道是啥原因。一次带。号，一次不带。过滤带。号的内容。
     *                2.判断语音输入内容，如果是提示规则的内容。则提示好的。否则，提示不知道在说什么。
     *                3.
     */
    private void dealContent(String content) {
        if (StringUtil.isEmpty(content)) {
            return;
        }
        if (content.contains("。")) {
            return;
        }
        if (StringUtil.isEqual("是", content)) {
            // TODO: 2019/5/9 判断是否输入完整
            if (myList.get(myList.size() - 1).isComplete()) {
                ViewUtils.makeToast(context, "保存计划", 1000);
                goPreviewPlan();
                return;
            }
        }
        if (StringUtil.isEqual("不保存", content)) {
            // TODO: 2019/5/9 判断是否输入完整
            if (myList.get(myList.size() - 1).isComplete()) {
                myList.clear();
                myList.add(new VoiceEntity(VoiceEntity.LEFT_TYPE_NOTICE, Constant.VOICE_NOTICE));
                voiceAdapter.setNewData(myList);
                return;
            }
        }
        if (content.contains("地区")) {
            if (CommonUtils.isFastDoubleClick2()) {
                return;
            }
            getCityId(content, VoiceAdapter.dealString(content));
            return;
        }
        addMessage(content);
    }

    /**
     * 进入制定计划页面
     */
    private void goPreviewPlan() {
        previewPlanModel.setIsLuodi("1");
        previewPlanModel.setIsZiXuan("1");

        previewPlanModel.setZhia(false);

        Intent intent = new Intent(context, PreviewPlanActivity.class);
        intent.putExtra("previewModel", previewPlanModel);
        intent.putExtra("bindCard", mBindCard);
        startActivity(intent);
    }

    /**
     * 解析计划内容,用于已输入的计划内容显示
     */
    private VoiceEntity getPlanItem() {
        VoiceEntity planVoiceEntity = new VoiceEntity().setItemType(VoiceEntity.LEFT_YTPE_CONTENT);
        for (int i = 1; i < myList.size(); i++) {
            VoiceEntity entity = myList.get(i);
            if (entity.getItemType() != VoiceEntity.RIGHT_TYPE) {
                continue;
            }
            String content = entity.getInputContent();
            LogUtils.i("content=" + content);
            if (StringUtil.isEmpty(content)) {
                return planVoiceEntity;
            }
            if (content.contains("金额")) {
                //金额
                if (isNunmber(VoiceAdapter.dealString(content))) {
                    planVoiceEntity.setMoney(VoiceAdapter.dealString(content));
                }
            } else if (content.contains("开始时间")) {
                //开始时间
                if (Utils.isTime(VoiceAdapter.dealString(content))) {
                    planVoiceEntity.setStartTime(VoiceAdapter.dealString(content));
                }
            } else if (content.contains("结束时间")) {
                //结束时间
                if (Utils.isTime(VoiceAdapter.dealString(content))) {
                    planVoiceEntity.setEndTime(VoiceAdapter.dealString(content));
                }
            } else if (content.contains("地区")) {
                //地区
                if (!StringUtil.isEmpty(VoiceAdapter.dealString(content))) {
                    planVoiceEntity.setArea(VoiceAdapter.dealString(content));
                }
            }
        }
        return planVoiceEntity;
    }

    //判断字符串是不是数字
    private boolean isNunmber(String filterString) {
        if (StringUtil.isEmpty(filterString)) {
            return false;
        }
        Matcher isNum = NUMBER_PATTERN.matcher(filterString);
        if (isNum.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 判断语音输入内容是否为指定规则
     * 判断逻辑顺序：
     * 1.是否包含关键字，金钱时间地区。时间还要包含年月日到
     * 2.替换关键字，如果是金额，判断剩下的内容是否为数字
     */
    private boolean isCorrectFormat(String content) {
        for (String str : strs) {
            if (content.contains(str)) {
                String filterString = VoiceAdapter.dealString(content);
                if (StringUtil.isEqual(str, "金额")) {
                    return isNunmber(filterString);
                }
                if (str.contains("时间")) {
                    if (content.contains("年") && content.contains("月")) {
                        return Utils.isTime(filterString);
                    } else {
                        return false;
                    }
                }
                if (StringUtil.isEmpty(filterString)) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 获取省市id
     *
     * @param content 包含地区文字的输入内容
     * @param name    去掉地区文字的输入内容
     */
    private void getCityId(final String content, final String name) {
        loadingDialog.show();
        HttpParams httpParams = OkClient.getParamsInstance().getParams();
        httpParams.put("3", "490005");
        httpParams.put("41", name);
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {
            @Override
            public void onError(Response<BaseEntity> response) {
                loadingDialog.dismiss();
                addWrongMessage(content);
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {

                BaseEntity model = response.body();
                if (model == null) {
                    loadingDialog.dismiss();
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    LogUtils.i("model.getStr25()=" + model.getStr25());
                    if (StringUtil.isEmpty(model.getStr25())) {
                        loadingDialog.dismiss();
                        addWrongMessage(content);
                        return;
                    }
                    ProvinceModel provinceModel = new ProvinceModel();
                    provinceModel.setProvince(model.getStr22());
                    provinceModel.setProId(model.getStr23());
                    provinceModel.setCity(model.getStr24());
                    provinceModel.setCityId(model.getStr25());
                    requestIndustry(provinceModel, content);
                } else {
                    loadingDialog.dismiss();
                    addWrongMessage(content);
                }
            }


        });
    }


    /**
     * 获取行业列表
     *
     * @param provinceModel 包含省市名称及id
     * @param content       包含地区文字的输入内容
     */
    private void requestIndustry(final ProvinceModel provinceModel, final String content) {
        loadingDialog.show();
        HttpParams httpParams = new HttpParams();
        httpParams.put("3", "490006");
        httpParams.put("30", mBindCard.getID());
        httpParams.put("32", provinceModel.getCityId());
        httpParams.put("33", mBindCard.getAcqCode());
        OkClient.getInstance().post(httpParams, new OkClient.EntityCallBack<BaseEntity>(context, BaseEntity.class) {


            @Override
            public void onError(Response<BaseEntity> response) {
                loadingDialog.dismiss();
                addWrongMessage(content);
            }

            @Override
            public void onSuccess(Response<BaseEntity> response) {
                loadingDialog.dismiss();
                BaseEntity model = response.body();
                if (model == null) {
                    return;
                }
                if ("00".equals(model.getStr39())) {
                    LogUtils.i("model.getStr38()=" + model.getStr38());
                    List<IndustryModel> list = JSONArray.parseArray(model.getStr38(), IndustryModel.class);
                    if (list != null && list.size() != 0) {

//                        if (area == null) {
                        HashMap<String, Area> map = new HashMap<>();
                        Area proArea = new Area();
                        proArea.setId("0");
                        proArea.setDivisionName(provinceModel.getProvince());
                        map.put("province", proArea);

                        Area cityArea = new Area();
                        cityArea.setId(provinceModel.getCityId());
                        cityArea.setDivisionName(provinceModel.getCity());
                        map.put("city", cityArea);
                        previewPlanModel.setArea(map);
                        previewPlanModel.setIndustryJson(model.getStr38());

                        voiceAdapter.setPreviewPlanModel(previewPlanModel);
                        addMessage(content);

                    } else {
                        // TODO: 2019/5/9 没有商户
                        addWrongMessage(content);
                    }


                } else {
                    addWrongMessage(content);
                }


            }


        });
    }

    /**
     * 根据输入内容更新列表
     */
    private void addMessage(String content) {
        VoiceEntity entity = myList.get(myList.size() - 1);
        myList.remove(myList.size() - 1);
        myList.add(new VoiceEntity(VoiceEntity.RIGHT_TYPE, content));
        setPlanEntity(entity, content);
//        VoiceEntity entity = getPlanItem();
        myList.add(new VoiceEntity().setItemType(VoiceEntity.LEFT_TYPE_NOTICE).setInputContent(isCorrectFormat(content) ? "好的," + getNextInputNotice(entity) : "未识别，" + getNextInputNotice(entity)));
        myList.add(entity);
        voiceAdapter.notifyDataSetChanged();
        rvList.smoothScrollToPosition(myList.size() - 1);
    }

    private void setPlanEntity(VoiceEntity entity, String content) {
        if (StringUtil.isEmpty(content)) {
            return;
        }
        if (content.contains("金额")) {
            //金额
            if (isNunmber(VoiceAdapter.dealString(content))) {
                entity.setMoney(VoiceAdapter.dealString(content));
            }
        } else if (content.contains("开始时间")) {
            //开始时间
            if (Utils.isTime(VoiceAdapter.dealString(content))) {
                entity.setStartTime(VoiceAdapter.dealString(content));
            }
        } else if (content.contains("结束时间")) {
            //结束时间
            if (Utils.isTime(VoiceAdapter.dealString(content))) {
                entity.setEndTime(VoiceAdapter.dealString(content));
            }
        } else if (content.contains("地区")) {
            //地区
            if (!StringUtil.isEmpty(VoiceAdapter.dealString(content))) {
                entity.setArea(VoiceAdapter.dealString(content));
            }
        }
    }

    private void addWrongMessage(String content) {
        VoiceEntity entity = myList.get(myList.size() - 1);
        myList.remove(myList.size() - 1);
//        VoiceEntity entity = getPlanItem();
        myList.add(new VoiceEntity(VoiceEntity.RIGHT_TYPE, content));
        myList.add(new VoiceEntity().setItemType(VoiceEntity.LEFT_TYPE_NOTICE).setInputContent("未识别，" + getNextInputNotice(entity)));
        myList.add(entity);
        voiceAdapter.notifyDataSetChanged();
        rvList.smoothScrollToPosition(myList.size() - 1);
    }

    /**
     * 提示未输入内容
     */
    private String getNextInputNotice(VoiceEntity voiceEntity) {
        if (StringUtil.isEmpty(voiceEntity.getMoney())) {
            return "请输入还款金额，例：\n金额2000";
        }
        if (StringUtil.isEmpty(voiceEntity.getStartTime())) {
            return "请输入还款开始时间，例：\n开始时间：2019年4月18";
        }
        if (StringUtil.isEmpty(voiceEntity.getEndTime())) {
            return "请输入还款结束时间，例：\n结束时间：2019年4月18";
        }
        if (StringUtil.isEmpty(voiceEntity.getArea())) {
            return "请输入地区，例：\n地区浙江省临海市。\n若是直辖市请输入如：\n地区北京市北京市";
        }
        return "信息输入完整，如需修改内容，只需再次语音输入覆盖原先内容";
    }

    private void printTransResult(RecognizerResult results) {
        String trans = JsonParser.parseTransResult(results.getResultString(), "dst");
        String oris = JsonParser.parseTransResult(results.getResultString(), "src");

        if (TextUtils.isEmpty(trans) || TextUtils.isEmpty(oris)) {
            showTip("解析结果失败，请确认是否已开通翻译功能。");
        } else {
            showTip("原始语言:\n" + oris + "\n目标语言:\n" + trans);
        }

    }

    @Override
    protected void onPause() {
        // 开放统计 移动数据统计分析
        FlowerCollector.onPageEnd(TAG);
        FlowerCollector.onPause(context);
        super.onPause();
    }

    @Override
    protected void onResume() {
        // 开放统计 移动数据统计分析
        FlowerCollector.onResume(context);
        FlowerCollector.onPageStart(TAG);
        super.onResume();
    }

    //执行音频流识别操作
    private void executeStream() {
        buffer.setLength(0);
        mIatResults.clear();
        // 设置参数
        setParam();
        // 设置音频来源为外部文件
        mIat.setParameter(SpeechConstant.AUDIO_SOURCE, "-1");
        // 也可以像以下这样直接设置音频文件路径识别（要求设置文件在sdcard上的全路径）：
        // mIat.setParameter(SpeechConstant.AUDIO_SOURCE, "-2");
        //mIat.setParameter(SpeechConstant.ASR_SOURCE_PATH, "sdcard/XXX/XXX.pcm");
        ret = mIat.startListening(mRecognizerListener);
        if (ret != ErrorCode.SUCCESS) {
            showTip("识别失败,错误码：" + ret);
        } else {
            byte[] audioData = FucUtil.readAudioFile(context, "iattest.wav");

            if (null != audioData) {
                showTip(getString(R.string.text_begin_recognizer));
                // 一次（也可以分多次）写入音频文件数据，数据格式必须是采样率为8KHz或16KHz（本地识别只支持16K采样率，云端都支持），
                // 位长16bit，单声道的wav或者pcm
                // 写入8KHz采样的音频时，必须先调用setParameter(SpeechConstant.SAMPLE_RATE, "8000")设置正确的采样率
                // 注：当音频过长，静音部分时长超过VAD_EOS将导致静音后面部分不能识别。
                // 音频切分方法：FucUtil.splitBuffer(byte[] buffer,int length,int spsize);
                mIat.writeAudio(audioData, 0, audioData.length);

                mIat.stopListening();
            } else {
                mIat.cancel();
                showTip("读取音频流失败");
            }
        }
    }

    /**
     * 提交计划后，自动关闭页面
     *
     * @param event
     */
    @Subscribe
    public void onEvent(PlanCloseEvent event) {
        ViewUtils.overridePendingTransitionBack(context);
    }
}
