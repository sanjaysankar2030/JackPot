# Recommended Research Papers for Building a BPE Tokenizer in Java

While implementing your Java BPE tokenizer (inspired by Hugging Face tokenizers, Karpathy’s `minbpe`, and supporting tiktoken-style byte-level BPE, SentencePiece, and WordPiece), the following foundational papers provide the theoretical and algorithmic background for each technique. These papers explain the core algorithms, design choices, and trade-offs you will encounter when making the implementation NLP-agnostic and compatible across variants.

## Byte Pair Encoding (BPE) – Original Data Compression Algorithm
- **Gage, P. (1994)**. A New Algorithm for Data Compression. *C Users Journal*.

## Byte Pair Encoding (BPE) for NLP / Subword Tokenization
- **Sennrich, R., Haddow, B., & Birch, A. (2016)**. Neural Machine Translation of Rare Words with Subword Units. *Proceedings of the 54th Annual Meeting of the Association for Computational Linguistics* (arXiv:1508.07909).

## WordPiece Tokenization
- **Schuster, M., & Nakajima, K. (2012)**. Japanese and Korean Voice Search. *IEEE International Conference on Acoustics, Speech and Signal Processing (ICASSP)*.

## SentencePiece (Language-Independent Subword Tokenizer with BPE + Unigram LM Support)
- **Kudo, T., & Richardson, J. (2018)**. SentencePiece: A simple and language independent subword tokenizer and detokenizer for Neural Text Processing. *Proceedings of the 2018 Conference on Empirical Methods in Natural Language Processing: System Demonstrations* (arXiv:1808.06226).

## Byte-Level BPE (tiktoken / GPT-2 Style Preprocessing)
- The byte-level variant is a practical extension popularized in the GPT-2 implementation; the algorithmic foundation remains the same as Sennrich et al. (2016) above, with the regex-based pre-tokenization step described in the GPT-2 paper:  
  **Radford, A., et al. (2019)**. Language Models are Unsupervised Multitask Learners. *OpenAI Technical Report*.

---

**Reading Recommendation**  
Start with the original Gage (1994) paper for the pure BPE merge algorithm, then move to Sennrich et al. (2016) for its adaptation to NLP. Read Schuster & Nakajima (2012) and Kudo & Richardson (2018) next, as they directly explain the differences in WordPiece and SentencePiece that you will need to support in a single, flexible Java implementation. These papers are concise and contain the exact pseudocode and design rationale used in production tokenizers like Hugging Face’s and tiktoken.


