package sagak.sagaksagak.domain.wrapper.member;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Address {

    private final String address;
    private final String addressDetail;

    private Address(String address, String addressDetail) {
        this.address = address;
        this.addressDetail = addressDetail;
    }

    public static Address of(String address, String addressDetail) {

        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("주소를 입력하세요.");
        }
        if (addressDetail == null || addressDetail.isEmpty()) {
            throw new IllegalArgumentException("상세주소를 입력하세요.");
        }
        return new Address(address, addressDetail);
    }
}
