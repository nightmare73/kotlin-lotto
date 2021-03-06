package lotto.domain

interface LottoTicketGenerateStrategy {

    fun createAutoTicket(): LottoTicket

    fun createManualTicket(numbers: IntArray): LottoTicket
}
