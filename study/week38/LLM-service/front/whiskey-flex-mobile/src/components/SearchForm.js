"use client"

import { useState } from "react"
import { Send } from "lucide-react"
import "./SearchForm.css"

const SearchForm = ({ onSearch }) => {
  const [query, setQuery] = useState("")
  const [isLoading, setIsLoading] = useState(false)

  const handleSubmit = async (e) => {
    e.preventDefault();
    const trimmedQuery = query.trim();
    if (!trimmedQuery) return;

    setIsLoading(true);
    setQuery("");
    try {
      await onSearch(trimmedQuery);
    } finally {
      setIsLoading(false);
    }
  }

  return (
    <form className="search-form" onSubmit={handleSubmit}>
      <div className="search-form-container">
        <input
          type="text"
          value={query}
          onChange={(e) => setQuery(e.target.value)}
          placeholder="원하시는 위스키 또는 취향을 알려주세요."
          className="search-input"
        />
        <button type="submit" className="send-button" disabled={!query.trim() || isLoading}>
          {isLoading ? (
            <div className="loading-spinner"></div>
          ) : (
            <Send size={20} />
          )}
        </button>
      </div>
    </form>
  )
}

export default SearchForm
