package sealed

/**
 * sealed class는 enum 클래스의 확장형이라고 설명하고 있는데
 * Enum 클래스는 아래와 같이 멤퍼 프로퍼티나, 메소드를 다르게 줄 수 없지만 sealed 클래스는 가능하기 때문이다.
 */
sealed class SealedDeliveryStatus {
    class WAIT(var code: Int, var codeName: String) : SealedDeliveryStatus()
    class CONSIGN(var code: Int, var codeName: String, val riderName: String) : SealedDeliveryStatus()
    class PICKUP(var code: Int, var codeName: String) : SealedDeliveryStatus()
    class COMPLETE(var code: Int, var codeName: String, var completeTime: String) : SealedDeliveryStatus(){
        fun getCodeWithCompleteTime() = "$code $completeTime"
    }
}

/**
 * 각 상태에 맞는 멤버프로퍼티/메소드를 다르게 리턴 하도록 처리 가능하다.
 */
fun selectSealedDeliveryStatus(deliveryStatus: SealedDeliveryStatus): String {
    return when (deliveryStatus) {
        is SealedDeliveryStatus.WAIT -> deliveryStatus.codeName
        is SealedDeliveryStatus.CONSIGN -> deliveryStatus.riderName
        is SealedDeliveryStatus.PICKUP -> deliveryStatus.code.toString()
        is SealedDeliveryStatus.COMPLETE -> deliveryStatus.getCodeWithCompleteTime()
    }
}

fun main () {
    val deliveryStatus = SealedDeliveryStatus.WAIT(1, "WAIT")
    val deliveryStatus2 = SealedDeliveryStatus.CONSIGN(2, "CONSIGN", "rider")
    val deliveryStatus3 = SealedDeliveryStatus.PICKUP(3, "PICKUP")
    val deliveryStatus4 = SealedDeliveryStatus.COMPLETE(4, "COMPLETE", "2021-01-01")

    println(selectSealedDeliveryStatus(deliveryStatus))
    println(selectSealedDeliveryStatus(deliveryStatus2))
    println(selectSealedDeliveryStatus(deliveryStatus3))
    println(selectSealedDeliveryStatus(deliveryStatus4))
}
