package wrx.web.gmall.service;


import wrx.web.gmall.bean.PaymentInfo;

public interface PaymentService {
    void savePaymentInfo(PaymentInfo paymentInfo);

    void updatePayment(PaymentInfo paymentInfo);
}
