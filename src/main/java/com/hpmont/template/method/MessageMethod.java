package com.hpmont.template.method;

import com.hpmont.util.SpringUtils;
import com.google.common.base.Strings;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2016/6/7.
 */
@Component("messageMethod")
public class MessageMethod implements TemplateMethodModelEx {

    @SuppressWarnings("rawtypes")
    public Object exec(List arguments) throws TemplateModelException {
        if (arguments != null && !arguments.isEmpty() && arguments.get(0) != null && !Strings.isNullOrEmpty(arguments.get(0).toString())) {
            String message = null;
            String code = arguments.get(0).toString();
            if (arguments.size() > 1) {
                Object[] args = arguments.subList(1, arguments.size()).toArray();
                message = SpringUtils.getMessage(code, args);
            } else {
                message = SpringUtils.getMessage(code);
            }
            return new SimpleScalar(message);
        }
        return null;
    }

}

