package com.jim.captcha.service.impl;


import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jim.captcha.service.CaptchaService;
import com.jim.captcha.utils.color.RandomColorFactory;
import com.jim.captcha.utils.encoder.EncoderHelper;
import com.jim.captcha.utils.predefined.CurvesRippleFilterFactory;
import com.jim.captcha.utils.predefined.DiffuseRippleFilterFactory;
import com.jim.captcha.utils.predefined.DoubleRippleFilterFactory;
import com.jim.captcha.utils.predefined.MarbleRippleFilterFactory;
import com.jim.captcha.utils.predefined.WobbleRippleFilterFactory;
import com.jim.captcha.utils.service.Captcha;
import com.jim.captcha.utils.service.ConfigurableCaptchaService;

/**
 * Created by Jim on 2016/5/18.
 * This class is ...
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {
    @Autowired
    private ConfigurableCaptchaService configurableCaptchaService;

    @Autowired
    private RandomColorFactory randomColorFactory;

    @Value("${captcha.destination}")
    private String path;
    private String file;
	private String word;


	public void setWord(String word) {
		this.word = word;
	}

	@Override
    public String getWord() {
        return this.word;
    }

	@Override
	public String getCaptchaUrl(HttpServletRequest req) {
		String scheme = req.getScheme();             // http
		String serverName = req.getServerName();     // hostname.com
		int serverPort = req.getServerPort();        // 80
		String contextPath = req.getContextPath();   // /mywebapp
		String servletPath = req.getServletPath();   // /servlet/MyServlet
		String pathInfo = req.getPathInfo();         // /a/b;c=123
		String queryString = req.getQueryString();          // d=789

		// Reconstruct original requesting URL
		StringBuffer url =  new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}

		url.append(contextPath).append(servletPath);

		if (pathInfo != null) {
			url.append(pathInfo);
		}
		if (queryString != null) {
			url.append("?").append(queryString);
		}
		return url.toString();
	}


	public void createDestination(){
		try {
			File file = new File(this.path);
			if (!file.isDirectory() || !file.exists()){
				FileUtils.forceMkdir(file);
			}
		}catch (SecurityException ex){
			ex.fillInStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    @Override
    public boolean validate(String word) {
        return false;
    }

    @Override
    public void generateImage() {
		this.createDestination();

        randomColorFactory.setMin(new Color(10, 10, 15));
        randomColorFactory.setMax(new Color(255, 30, 10));
        configurableCaptchaService.setColorFactory(randomColorFactory);
        int counter = new Random().nextInt() % 5;

        switch (counter) {
            case 0:
                configurableCaptchaService.setFilterFactory(new CurvesRippleFilterFactory(configurableCaptchaService.getColorFactory()));
                break;
            case 1:
                configurableCaptchaService.setFilterFactory(new MarbleRippleFilterFactory());
                break;
            case 2:
                configurableCaptchaService.setFilterFactory(new DoubleRippleFilterFactory());
                break;
            case 3:
                configurableCaptchaService.setFilterFactory(new WobbleRippleFilterFactory());
                break;
            case 4:
                configurableCaptchaService.setFilterFactory(new DiffuseRippleFilterFactory());
                break;
			default:
				configurableCaptchaService.setFilterFactory(new CurvesRippleFilterFactory(configurableCaptchaService.getColorFactory()));
        }
        FileOutputStream fos = null;

        try {
            this.file = UUID.randomUUID().toString() + ".png";
            File tmp = new File(this.path + "/" + this.file);
            fos = new FileOutputStream(tmp);
            Captcha result = EncoderHelper.getChallangeAndWriteImage(configurableCaptchaService, "png", fos);
			this.setWord(result.getChallenge());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getFile() {
        return this.file;
    }
}
