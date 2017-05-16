package com.excellent.dm.ui.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dm.excellent.baselibrary.utils.ImageLoadUtil;
import com.dm.excellent.baselibrary.utils.LogUtils;
import com.excellent.dm.R;
import com.excellent.dm.base.App;
import com.excellent.dm.base.AppManager;
import com.excellent.dm.base.BaseCameraActivity;
import com.excellent.dm.net.ApiResult;
import com.excellent.dm.net.JsonCallback;
import com.excellent.dm.net.Urls;
import com.excellent.dm.ui.activity.poi.SelectSPMAddressActivity;
import com.excellent.dm.utils.CommonUtils;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.PostRequest;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;

public class SPMActivity extends BaseCameraActivity implements View.OnClickListener {


    @BindView(R.id.civ)
    CircleImageView civ;

    String Name = "-1", Confines = "-1", Notice = "-1", Contacts = "-1", Phone = "-1", BusinessBeginTime = "-1", BusinessEndTime = "-1";
    @BindView(R.id.tv_spm_1)
    TextView tvSpm1;
    @BindView(R.id.tv_spm_2)
    TextView tvSpm2;
    @BindView(R.id.tv_spm_3)
    TextView tvSpm3;
    @BindView(R.id.tv_spm_4)
    TextView tvSpm4;
    @BindView(R.id.tv_spm_5)
    TextView tvSpm5;
    @BindView(R.id.tv_spm_6)
    TextView tvSpm6;
    @BindView(R.id.tv_spm_7)
    TextView tvSpm7;
    @BindView(R.id.tv_spm_8)
    TextView tvSpm8;
    @BindView(R.id.tv_spm_9)
    TextView tvSpm9;
    @BindView(R.id.tv_spm_10)
    TextView tvSpm10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spm);
        ButterKnife.bind(this);
        getBar().initActionBar("超市信息", this);
        ImageLoadUtil.setImage(App.spm.getLogoUrl(), civ, this);
        CommonUtils.setText(tvSpm3, App.spm.getName());
        CommonUtils.setText(tvSpm2, App.spm.getConfines());
        CommonUtils.setText(tvSpm4, App.spm.getContacts());
        CommonUtils.setText(tvSpm5, App.spm.getPhone());
        CommonUtils.setText(tvSpm6, App.spm.getAddressContent());
        CommonUtils.setText(tvSpm7, App.spm.getBusinessBeginTime()+"-"+App.spm.getBusinessEndTime());
        CommonUtils.setText(tvSpm10, App.spm.getNotice());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                AppManager.getInstance().killActivity(this);
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.ll_spm_0, R.id.ll_spm_1, R.id.ll_spm_2, R.id.ll_spm_3, R.id.ll_spm_4, R.id.ll_spm_5, R.id.ll_spm_6, R.id.ll_spm_7, R.id.ll_spm_8, R.id.ll_spm_9, R.id.ll_spm_10})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_spm_0:
                openAlbum(400, 400, true, 101);
                break;
            case R.id.ll_spm_1:
                break;
            case R.id.ll_spm_2:
                break;
            case R.id.ll_spm_3:
                break;
            case R.id.ll_spm_4:
                break;
            case R.id.ll_spm_5:
                break;
            case R.id.ll_spm_6:
                startActivity(SelectSPMAddressActivity.class);
                break;
            case R.id.ll_spm_7:
                break;
            case R.id.ll_spm_8:
                break;
            case R.id.ll_spm_9:
                break;
            case R.id.ll_spm_10:
                break;
        }
    }

    @Override
    protected void picSelectSuccess(File bitmap, int code) {
        if (code == 101) {
            Glide.with(this).load(bitmap).into(civ);
            uploadIcon(bitmap);
        }
    }

    private void uploadIcon(File file) {
        OkGo.post(Urls.UpFiles_P)
                .params("file", file)
                .execute(new JsonCallback<ApiResult<String>>(new TypeToken<ApiResult<String>>() {
                }.getType()) {
                    @Override
                    public void onSuccess(ApiResult<String> result, Call call, Response response) {
                        try {
                            if (result.isSuccess()) {
                                if (null == result.getObj()) {
                                    showToast("修改失败");
                                    return;
                                }
                                changeIcon(result.getObj());
                            } else {
                                showToast(result.getMsg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        showToast(getResources().getString(R.string.action_failure));
                        LogUtils.e(e.getMessage());
                    }
                });

    }

    private void changeIcon(String url) {
        OkGo.post(Urls.ModifySupermarketLogo)
                .params("SupermarketCode", App.spm.getSupermarketCode())
                .params("LogoUrl", url)
                .execute(new JsonCallback<ApiResult<Object>>(new TypeToken<ApiResult<Object>>() {
                }.getType()) {
                    @Override
                    public void onSuccess(ApiResult<Object> result, Call call, Response response) {
                        try {
                            if (result.isSuccess()) {
                                showToast("修改成功");
                            } else {
                                showToast(result.getMsg());
                            }
                        } catch (Exception e) {
                            showToast("修改失败");
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        showToast(getResources().getString(R.string.action_failure));
                        LogUtils.e(e.getMessage());
                    }
                });

    }


    private void updateSPMData() {
        PostRequest postRequest = OkGo.post(Urls.ModifySupermarketInfo_P)
                .params("SupermarketCode", App.spm.getSupermarketCode());
        if (!Name.equals("-1")) {
            postRequest.params("Name", Name);
        }

        if (!Confines.equals("-1")) {
            postRequest.params("Confines", Confines);
        }
        if (!Notice.equals("-1")) {
            postRequest.params("Notice", Notice);
        }
        if (!Contacts.equals("-1")) {
            postRequest.params("Contacts", Contacts);
        }
        if (!Phone.equals("-1")) {
            postRequest.params("Phone", Phone);
        }

        if (!BusinessBeginTime.equals("-1")) {
            postRequest.params("BusinessBeginTime", BusinessBeginTime);
        }
        if (!BusinessEndTime.equals("-1")) {
            postRequest.params("BusinessEndTime", BusinessEndTime);
        }
        postRequest.execute(new JsonCallback<ApiResult<Object>>(new TypeToken<ApiResult<Object>>() {
        }.getType()) {
            @Override
            public void onSuccess(ApiResult<Object> result, Call call, Response response) {
                try {
                    if (result.isSuccess()) {
                        showToast("修改成功");
                    } else {
                        showToast(result.getMsg());
                    }
                } catch (Exception e) {
                    showToast("修改失败");
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                showToast(getResources().getString(R.string.action_failure));
                LogUtils.e(e.getMessage());
            }
        });

    }
}
