package com.fpt.topfood_be.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User customer;

    private String receiverName;
    private String receiverMobile;

    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;

    @OneToMany
    private List<OrderItem> items;

    @ManyToOne
    private Address deliveryAddress;

    // Ghi chú bởi: Phùng Hoàng
    // Trường này khi tạo mới sẽ đóng vai trò lưu thời gian dự tính giao đến
    // VD: Đơn hàng sẽ được giao tới bạn lúc 11:35 PM (Dự tính)
    // Sau khi giao xong thì cập nhật thời gian mới là thời gian hoàn thành đơn
    // VD: Đơn hàng đã được giao tới bạn lúc 11:25 PM
    private Date deliveryAt;

    private Long deliveryFee;

    // Ghi chú bởi: Phùng Hoàng
    // Tổng giá này đã bao gồm tiền của add-ons tức là món ăn + các tùy chọn thêm
    // VD: trà sữa (30k) + topping chân trâu (5k)
    // VD: nước pepsi (10k) + upsize(L) (5k)
    private Long totalPrice;

    private String orderStatus;

    // Ghi chú bởi: Phùng Hoàng
    // Khách hàng có 2 hình thức thanh toán
    // 1 - Trả tiền mặt khi nhận hàng (Khi chưa thanh toán thì Payment Status là "COD")
    // 2 - Thanh toán điện tử qua Stripe (Khi chưa thanh toán thì Payment Status là "NOT PAID")
    // Sau khi đã thanh toán xong thì Payment Status là "PAID"
    private String paymentStatus;

    private Date createdAt;

}
