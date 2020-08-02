package lotto

class LottoTicket(lottoNumbers: List<LottoNumber>) {
    private val numbers: Set<LottoNumber>

    init {
        validateLottoNumbers(lottoNumbers)
        numbers = lottoNumbers.toSet()
    }

    private fun validateLottoNumbers(lottoNumbers: List<LottoNumber>) {
        require(lottoNumbers.size == LOTTO_NUMBER_COUNT) { "로또 번호는 반드시 6개 입니다." }
        require(lottoNumbers.distinct().size == LOTTO_NUMBER_COUNT) { "로또 번호는 중복될 수 없습니다." }
    }

    fun compare(lottoTicket: LottoTicket): LottoResult {
        return this.numbers
            .count { lottoTicket.has(it) }
            .let { LottoResult.findByMatch(it) }
    }

    private fun has(lottoNumber: LottoNumber): Boolean {
        return this.numbers.contains(lottoNumber)
    }

    companion object {
        private const val LOTTO_NUMBER_COUNT = 6
    }
}