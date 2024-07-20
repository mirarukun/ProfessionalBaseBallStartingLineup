/**
 * トップページ用のJavaScript
 */

// コメント投稿の非同期通信処理
const commentPost = () => {
    const commentSubmit = document.getElementById("commentSubmit");

    commentSubmit.addEventListener("click", () => {
        const commentText = document.getElementById("commentText").value;
        const startingLineupId = document.getElementById("StartingLineupId").value;

        const data = JSON.stringify({
            text: commentText,
            StartingLineupId: startingLineupId
        });

        const XHR = new XMLHttpRequest();
        XHR.open("POST", "/addComment", true);
        XHR.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        XHR.responseType = "json";

        XHR.onload = () => {
            if (XHR.status === 200) {
                alert("コメントが投稿されました");

                const addContent = document.getElementById("addContent");
                const newComment = document.createElement("div");
                newComment.innerHTML = `<h4>${XHR.response.text}</h4>`;

                addContent.prepend(newComment);

                document.getElementById("commentText").value = '';
            } else {
                alert("コメントの投稿に失敗しました");
            }
        };

        XHR.onerror = () => {
            alert("リクエスト中にエラーが発生しました");
        };

        XHR.send(data);
    });
};

// いいねボタン用JavaScript
const iineButtonHandler = () => {
    const iineButtons = document.querySelectorAll('.iineButton');

    iineButtons.forEach((iineButton) => {
		const iineId = iineButton.getAttribute('data-iine-id'); // 修正: 数値に変換しない
        
        if (!iineId) {
            console.error('いいねIDが取得できませんでした');
            return;
        }

        const iineCountSpan = iineButton.nextElementSibling;
        
        console.log('いいねボタン:', iineButton);
		console.log('いいねID:', iineId);
		console.log('いいねカウントスパン:', iineCountSpan);

        // ページロード時のいいね数の取得と表示
        fetch(`/count/${iineId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('いいね数の取得に失敗しました');
                }
                return response.json(); // JSON形式で取得
            })
            .then(data => {
				const count = data.count; // JSONオブジェクトから数値を取得
                iineCountSpan.textContent = count; // 数値を表示
            })
            .catch(error => {
                console.error('いいね数の取得時のエラー:', error);
            });

        // 各 iineButton にクリックイベントを設定し、クリックされたときにサーバーに対していいね数を増加させるリクエストを送信する
        iineButton.addEventListener('click', () => {
			console.log('いいねボタンがクリックされました:', iineId);
			
            fetch(`/increment/${iineId}`, {
                method: 'POST'
            })
            .then(response => {
				console.log('レスポンスを受信しました:', response);
                if (!response.ok) {
                    throw new Error('いいねの増加に失敗しました');
                }
                return response.json();
            })
            .then(data => {
				console.log('サーバーからのデータ:', data);
				//console.log('カウント:', data.count);　→これは間違いだった
                iineCountSpan.textContent = data
                //iineCountSpan.textContent = parseInt(data.count);　→このログで気づけた
            })
            .catch(error => {
                console.error('いいねの増加時のエラー:', error);
            });
        });
    });
};

// ページの読み込みが完了した際に、コメント投稿機能といいね機能を初期化
window.addEventListener('load', () => {
    commentPost();
    iineButtonHandler();
});
