/**
 * Copyright 2013-2015 JueYue (qrb.jueyue@gmail.com)
 *   
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jeecgframework.poi.test.excel.read.hanlder;

import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.jeecgframework.poi.test.entity.ExcelVerifyEntity;

public class ExcelVerifyHandlerImpl implements IExcelVerifyHandler<ExcelVerifyEntity> {

    @Override
    public ExcelVerifyHanlderResult verifyHandler(ExcelVerifyEntity obj) {
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isNotEmpty(obj.getEmail())) {
            builder.append("Email must null;");
        }
        if (obj.getMax() > 15) {
            builder.append("max must lt 15;");
        }
        return new ExcelVerifyHanlderResult(false, builder.toString());
    }

}
