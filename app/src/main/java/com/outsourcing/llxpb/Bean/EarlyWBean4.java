package com.outsourcing.llxpb.Bean;

import java.util.List;

/**
 * Created by DELL on 2019/9/30.
 */

public class EarlyWBean4 {


    /**
     * code : 0
     * message : success
     * data : {"subcontractor_stat":[{"output_month":201904,"subcontractor_id":5,"subcontractor_name":"武汉康华劳务分包有限公司","output_apply_amount":77627,"wage_apply_amount":21684,"potential_loss":55943,"potential_loss_percent":72.06642018885182},{"output_month":201905,"subcontractor_id":5,"subcontractor_name":"武汉康华劳务分包有限公司","output_apply_amount":73420,"wage_apply_amount":20944,"potential_loss":52476,"potential_loss_percent":71.47371288477254},{"output_month":201906,"subcontractor_id":5,"subcontractor_name":"武汉康华劳务分包有限公司","output_apply_amount":41160,"wage_apply_amount":21684,"potential_loss":19476,"potential_loss_percent":47.31778425655977},{"output_month":201908,"subcontractor_id":5,"subcontractor_name":"武汉康华劳务分包有限公司","output_apply_amount":77627,"wage_apply_amount":10842,"potential_loss":66785,"potential_loss_percent":86.03321009442591}],"summary_stat":{"output_apply_amount":462231,"wage_apply_amount":100801,"potential_loss":361430,"potential_loss_percent":78.1925054788623}}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * subcontractor_stat : [{"output_month":201904,"subcontractor_id":5,"subcontractor_name":"武汉康华劳务分包有限公司","output_apply_amount":77627,"wage_apply_amount":21684,"potential_loss":55943,"potential_loss_percent":72.06642018885182},{"output_month":201905,"subcontractor_id":5,"subcontractor_name":"武汉康华劳务分包有限公司","output_apply_amount":73420,"wage_apply_amount":20944,"potential_loss":52476,"potential_loss_percent":71.47371288477254},{"output_month":201906,"subcontractor_id":5,"subcontractor_name":"武汉康华劳务分包有限公司","output_apply_amount":41160,"wage_apply_amount":21684,"potential_loss":19476,"potential_loss_percent":47.31778425655977},{"output_month":201908,"subcontractor_id":5,"subcontractor_name":"武汉康华劳务分包有限公司","output_apply_amount":77627,"wage_apply_amount":10842,"potential_loss":66785,"potential_loss_percent":86.03321009442591}]
         * summary_stat : {"output_apply_amount":462231,"wage_apply_amount":100801,"potential_loss":361430,"potential_loss_percent":78.1925054788623}
         */

        private SummaryStatBean summary_stat;
        private List<SubcontractorStatBean> subcontractor_stat;

        public SummaryStatBean getSummary_stat() {
            return summary_stat;
        }

        public void setSummary_stat(SummaryStatBean summary_stat) {
            this.summary_stat = summary_stat;
        }

        public List<SubcontractorStatBean> getSubcontractor_stat() {
            return subcontractor_stat;
        }

        public void setSubcontractor_stat(List<SubcontractorStatBean> subcontractor_stat) {
            this.subcontractor_stat = subcontractor_stat;
        }

        public static class SummaryStatBean {
            /**
             * output_apply_amount : 462231.0
             * wage_apply_amount : 100801.0
             * potential_loss : 361430.0
             * potential_loss_percent : 78.1925054788623
             */

            private double output_apply_amount;
            private double wage_apply_amount;
            private double potential_loss;
            private double potential_loss_percent;

            public double getOutput_apply_amount() {
                return output_apply_amount;
            }

            public void setOutput_apply_amount(double output_apply_amount) {
                this.output_apply_amount = output_apply_amount;
            }

            public double getWage_apply_amount() {
                return wage_apply_amount;
            }

            public void setWage_apply_amount(double wage_apply_amount) {
                this.wage_apply_amount = wage_apply_amount;
            }

            public double getPotential_loss() {
                return potential_loss;
            }

            public void setPotential_loss(double potential_loss) {
                this.potential_loss = potential_loss;
            }

            public double getPotential_loss_percent() {
                return potential_loss_percent;
            }

            public void setPotential_loss_percent(double potential_loss_percent) {
                this.potential_loss_percent = potential_loss_percent;
            }
        }

        public static class SubcontractorStatBean {
            /**
             * output_month : 201904
             * subcontractor_id : 5
             * subcontractor_name : 武汉康华劳务分包有限公司
             * output_apply_amount : 77627.0
             * wage_apply_amount : 21684.0
             * potential_loss : 55943.0
             * potential_loss_percent : 72.06642018885182
             */

            private float output_month;
            private int subcontractor_id;
            private String subcontractor_name;
            private double output_apply_amount;
            private double wage_apply_amount;
            private double potential_loss;
            private double potential_loss_percent;

            public float getOutput_month() {
                return output_month;
            }

            public void setOutput_month(float output_month) {
                this.output_month = output_month;
            }

            public int getSubcontractor_id() {
                return subcontractor_id;
            }

            public void setSubcontractor_id(int subcontractor_id) {
                this.subcontractor_id = subcontractor_id;
            }

            public String getSubcontractor_name() {
                return subcontractor_name;
            }

            public void setSubcontractor_name(String subcontractor_name) {
                this.subcontractor_name = subcontractor_name;
            }

            public double getOutput_apply_amount() {
                return output_apply_amount;
            }

            public void setOutput_apply_amount(double output_apply_amount) {
                this.output_apply_amount = output_apply_amount;
            }

            public double getWage_apply_amount() {
                return wage_apply_amount;
            }

            public void setWage_apply_amount(double wage_apply_amount) {
                this.wage_apply_amount = wage_apply_amount;
            }

            public double getPotential_loss() {
                return potential_loss;
            }

            public void setPotential_loss(double potential_loss) {
                this.potential_loss = potential_loss;
            }

            public double getPotential_loss_percent() {
                return potential_loss_percent;
            }

            public void setPotential_loss_percent(double potential_loss_percent) {
                this.potential_loss_percent = potential_loss_percent;
            }
        }
    }
}
