package com.store.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
    public static boolean checkVerifyCode(HttpServletRequest request) {
        String verifyCodeExpected = (String) request.getSession()
                .getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String verifyCodeActual = HttpServletRequestUtil
                .getString(request, "verifyCodeActual");
        if (verifyCodeActual == null || !verifyCodeExpected.equals(verifyCodeActual))
            return false;
        else
            return true;
    }
}
