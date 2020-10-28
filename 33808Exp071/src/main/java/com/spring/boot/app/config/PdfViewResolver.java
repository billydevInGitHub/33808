package com.spring.boot.app.config;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import com.spring.boot.app.view.PdfView;
/**
 * Created by Basant on 2017-06-04.
 */
public class PdfViewResolver implements ViewResolver {
    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        if("pdf".equals(s.toLowerCase())){
            PdfView view = new PdfView();
            return view;
        }else{
            return null;
        }
    }
}
