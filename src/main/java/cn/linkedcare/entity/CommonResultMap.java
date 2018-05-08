package cn.linkedcare.entity;

import cn.linkedcare.enumeration.HttpCode;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Optional;

/**
 * 统一返回的实体类
 * Created by Benji on 2018/5/7.
 */
public class CommonResultMap extends HashMap {

    private CommonResultMap(CommonResultMapBuilder builder) {
        if (StringUtils.isNotBlank(builder.msg)) {
            super.put("MSG", builder.msg);
        }
        Optional.ofNullable(builder.data).ifPresent(data -> super.put("DATA", data));
        Optional.ofNullable(builder.total).ifPresent(total -> super.put("TOTAL", total));
        super.put("STATUS", builder.status);
    }

    public static CommonResultMapBuilder builder(HttpCode code) {
        CommonResultMapBuilder builder = new CommonResultMapBuilder();
        builder.status = code.getStatus();
        return builder;
    }


    /**
     * 内部Builder
     */
    public static class CommonResultMapBuilder {
        private String msg;
        private Object data;
        private Integer total;
        private String status;

        public CommonResultMapBuilder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public CommonResultMapBuilder data(Object data) {
            this.data = data;
            return this;
        }

        public CommonResultMapBuilder total(int total) {
            this.total = total;
            return this;
        }

        public CommonResultMap build() {
            return new CommonResultMap(this);
        }

    }


}
