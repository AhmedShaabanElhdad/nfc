package com.ahmed.nfc.utils

class AppConstant {
    companion object {

        val DB_NAME = "transaction.db"
        val PREF_NAME = "transaction_pref"

        const val DEBUG_TAG: String = ("AhmedShaabanDebug")

        //todo fix this problem here with domain later
        /// API FOR CRICKET DATA
        const val BASE_URL: String = ("http://ha-cg.com/api/")
        const val API_TRANSACTIOS: String = "getTransactions.php"
        const val API_ADDTRANSACTION: String = "addTransaction.php"

        const val TYPE: String = "type"
        const val COUNTRY: String = "country"
        const val VALUE: String = "value"
        const val CRYPTOGRAM: String = "cryptogram"
        const val API_TOKEN_VALUE: String = "hAsSaN17856942145asdnglwnflfbalddmlog85"
        const val API_TOKEN_KEY: String = "api_token"


    }
}