namespace(lib.FormTagLib).with {
        entry(title: 'Twitter ID', field:'twitterId') {
            textbox(default:'irof')
        }
        entry(title: 'Valid Regex for Tweet', field:'validRegexForTweet') {
            textbox(default:'.*(test|build|テスト|ビルド|い.*ろ.*ふ).*')
        }
}