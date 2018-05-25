package com.hpmont.service.adminuser.Impl;

import com.hpmont.constants.Setting;
import com.hpmont.service.adminuser.ICaptchaService;
import com.google.common.base.Strings;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;

/**
 * Created by Administrator on 2016/7/20.
 */
@Service("captchaServiceImpl")
public class CaptchaServiceImpl implements ICaptchaService {
    @Resource(name = "imageCaptchaService")
    private com.octo.captcha.service.CaptchaService imageCaptchaService;
    @Override
    public BufferedImage buildImage(String captchaId) {
        return (BufferedImage) imageCaptchaService.getChallengeForID(captchaId);
    }

    @Override
    public boolean isValid(Setting.CaptchaType captchaType, String captchaId, String captcha) {
        if (captchaType != null ) {
            if (!Strings.isNullOrEmpty(captchaId) && !Strings.isNullOrEmpty(captcha)) {
                try {
                    return imageCaptchaService.validateResponseForID(captchaId, captcha.toUpperCase());
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
}
