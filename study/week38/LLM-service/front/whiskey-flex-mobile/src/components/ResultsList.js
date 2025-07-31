import React, { useEffect, useState } from "react"
import { User, Bot } from "lucide-react"
import "./ResultsList.css"

const ResultsList = ({ results, query, isLoading }) => {
    const [chatHistory, setChatHistory] = useState([])

    // 사용자 질문 추가
    useEffect(() => {
        if (query) {
            setChatHistory((prev) => [
                ...prev,
                {
                    id: "user-" + Date.now(),
                    type: "user",
                    content: query,
                },
            ])
        }
    }, [query])

    // 로딩 메시지 관리
    useEffect(() => {
        if (isLoading) {
            setChatHistory((prev) => [
                ...prev,
                {
                    id: "ai-loading",
                    type: "ai",
                    content: "위스키를 검색중입니다...",
                    isLoading: true,
                },
            ])
        } else {
            // 로딩 메시지 제거
            setChatHistory((prev) =>
                prev.filter((msg) => msg.id !== "ai-loading")
            )
        }
    }, [isLoading])

    // 결과 메시지 추가
    useEffect(() => {
        if (results) {
            setChatHistory((prev) => [
                ...prev,
                {
                    id: "ai-response-" + Date.now(),
                    type: "ai",
                    content: results,
                },
            ])
        }
    }, [results])

    if (chatHistory.length === 0) {
        return (
            <div className="chat-container">
                <div className="empty-chat">
                    <div className="empty-chat-icon">🥃</div>
                    <h3>위스키 추천 AI</h3>
                    <p>
                        어떤 위스키를 찾고 계신가요?
                        <br />
                        취향이나 상황을 알려주시면 맞춤 추천해드릴게요!
                    </p>
                </div>
            </div>
        )
    }

    return (
        <div className="chat-container">
            <div className="chat-messages">
                {chatHistory.map((message) => (
                    <div key={message.id} className={`chat-message ${message.type}-message`}>
                        <div className="message-avatar">{message.type === "user" ? <User size={18} /> : <Bot size={18} />}</div>
                        <div className="message-bubble">
                            {message.isLoading ? (
                                <div className="loading-content">
                                    <div className="loading-dots">
                                        <span></span>
                                        <span></span>
                                        <span></span>
                                    </div>
                                    <span className="loading-text">{message.content}</span>
                                </div>
                            ) : (
                                <div className="message-text">{message.content}</div>
                            )}
                        </div>
                    </div>
                ))}
            </div>
        </div>
    )
}

export default ResultsList