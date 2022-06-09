use shoes_store;
CREATE TABLE `account` (
  `code_user` varchar(50) NOT NULL primary key,
  `user_name` varchar(255) DEFAULT NULL,
  `pass_word` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `permisson` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `account` (`code_user`, `user_name`, `pass_word`,`phone`, `permisson`) VALUES
('23ba47231b694b978744d0cc1741d47f', 'Dat', 'c4ca4238a0b923820dcc509a6f75849b', '0222222222',1),
('ca0a8f6b95f1444e80e7f9089f72b554', 'Admin', 'c4ca4238a0b923820dcc509a6f75849b','0222222222', 0);

CREATE TABLE `discount` (
  `code` int(11) NOT NULL primary key,
  `discount` int(11) DEFAULT NULL,
  `fromDate` datetime DEFAULT NULL,
  `toDate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `discount` (`code`, `discount`, `fromDate`, `toDate`) VALUES
(1, 30, '2020-09-05 00:00:00', '2020-09-05 00:00:00');

drop table khach_hang;
CREATE TABLE `khach_hang` (
  `code_kh` varchar(50) NOT NULL primary key,
  `name_kh` varchar(255) DEFAULT NULL,
  `dia_chi` varchar(500) DEFAULT NULL,
  `phone` varchar(14) DEFAULT NULL,
  `fb` varchar(500) DEFAULT NULL,
	`note` varchar(2000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `loai_sp` (
  `code_loai_sp` int NOT NULL primary key,
  `name_loai_sp` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `loai_sp` (`code_loai_sp`, `name_loai_sp`) VALUES
(0, 'Dịch vụ'),
(1, 'Sản phẩm');
drop table orders;
CREATE TABLE `orders` (
  `code_order` varchar(50) NOT NULL primary key,
  `code_user` varchar(50) NOT NULL,
  `code_kh` varchar(50) NOT NULL,
  `coc` double DEFAULT NULL,
  `giam_gia` double DEFAULT NULL,
  `tong_tien` double DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL, 
  `created_at` datetime DEFAULT NULL,
  `complete_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `order_detail` (
  `code_order_detail` varchar(50) NOT NULL primary key,
  `code_order` varchar(50) NOT NULL,
  `code_sp_detail` varchar(50) NOT NULL,
  `sl` int(11) DEFAULT NULL,
  `don_gia_xuat` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `san_pham` (
  `code_sp` varchar(50) NOT NULL primary key,
  `code_loai_sp` varchar(50) NOT NULL,
  `product_code` varchar(20) DEFAULT NULL,
  `name_sp` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `san_pham_detail` (
  `code_sp_detail` varchar(50) NOT NULL primary key,
  `code_sp` varchar(50) NOT NULL,
  `so_luong` int(11) DEFAULT NULL,
  `don_gia_nhap` double DEFAULT NULL,
  `don_gia_xuat` double DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `setting` (
  `code` int(11) NOT NULL primary key,
  `theme_name` varchar(100) DEFAULT NULL,
  `selling_method` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `setting`
--

INSERT INTO `setting` (`code`, `theme_name`, `selling_method`) VALUES
(1, 'Dark', 1);

CREATE TABLE `theme` (
  `nameTheme` varchar(255) NOT NULL primary key,
  `backgroundTextField` varchar(50) DEFAULT NULL,
  `backgroundButton` varchar(50) DEFAULT NULL,
  `backgroundMainPanel` varchar(50) DEFAULT NULL,
  `backgroundTittleTable` varchar(50) DEFAULT NULL,
  `backgroundTaskPanel` varchar(50) DEFAULT NULL,
  `backgroundTaskPanelClick` varchar(50) DEFAULT NULL,
  `backgroundMenuBar` varchar(50) DEFAULT NULL,
  `TxtColorTitle` varchar(50) DEFAULT NULL,
  `TxtColorTitleClick` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `theme`
--

INSERT INTO `theme` (`nameTheme`, `backgroundTextField`, `backgroundButton`, `backgroundMainPanel`, `backgroundTittleTable`, `backgroundTaskPanel`, `backgroundTaskPanelClick`, `backgroundMenuBar`, `TxtColorTitle`, `TxtColorTitleClick`) VALUES
('Colorfull', '#FFFFFF', '#bcbcbc', '#092c00', '#2097fc', '#4ad5f8', '#ff9000', '#660066', '#FFFFFF', '#000000'),
('Dark', '#FFFFFF', '#bcbcbc', '#464646', '#bcbcbc', '#D4D2D2', '#BFBFBF', '#2B313B', '#FFFFFF', '#000000'),
('Ocean', '#FFFFFF', '#bcbcbc', '#375e6d', '#3badfc', '#7bdafe', '#00baff', '#12318a', '#FFFFFF', '#002181'),
('Orange', '#FFFFFF', '#bcbcbc', '#782a03', '#ff9c00', '#fdcb60', '#ff9c00', '#da5f04', '#FFFFFF', '#793503'),
('While', '#FFFFFF', '#9d9c9c', '#FFFFFF', '#bcbcbc', '#ebebeb', '#d0d0d0', '#FFFFFF', '#000000', '#000000');


CREATE TABLE `thu_chi` (
  `code_thu_chi` int(10) NOT NULL primary key,
  `code_user` varchar(50) NOT NULL,
  `thu` double DEFAULT NULL,
  `chi` double DEFAULT NULL,
  `ton_cuoi` double DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `danh_sach_nhap`  
AS SELECT `a`.`name_kh` AS `name_kh`, `b`.`code_order` AS `code_order`, `b`.`tong_tien` AS `tong_tien`, `b`.`created_at` AS `created_at`, `c`.`user_name` AS `user_name` 
FROM ((`khach_hang` `a` join `orders` `b` on(`a`.`code_kh` = `b`.`code_kh`)) join `account` `c` on(`b`.`code_user` = `c`.`code_user`)) WHERE `b`.`status` = 'DRAFT' ;
