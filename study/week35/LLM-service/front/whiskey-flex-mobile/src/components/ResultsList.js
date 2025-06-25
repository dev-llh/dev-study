"use client"

import React from "react"
import { useState } from "react"
import { Filter, ArrowUpDown } from "lucide-react"
import { useWhiskeyStore } from "../store/whiskeyStore"
import WhiskeyCard from "./WhiskeyCard"
import FilterModal from "./FilterModal"
import "./ResultsList.css"

const ResultsList = () => {
  const { results, isLoading, query } = useWhiskeyStore()
  const [filterType, setFilterType] = useState(null)
  const [sortBy, setSortBy] = useState("match")
  const [showFilterModal, setShowFilterModal] = useState(false)

  const types = ["스카치", "아이리시", "아메리칸", "캐나디안", "재패니스"]

  const getSortLabel = () => {
    switch (sortBy) {
      case "match":
        return "관련도순"
      case "rating":
        return "평점순"
      case "price":
        return "가격순"
      default:
        return "관련도순"
    }
  }

  const filteredResults = results
    ? results.filter((whiskey) => !filterType || whiskey.type === filterType)
    : []

  if (!query) {
    return (
      <div className="empty-state">
        <div className="empty-state-content">
          <h2 className="empty-state-description">
            원하시는 위스키를 찾아드립니다.
          </h2>
        </div>
      </div>
    )
  }

  if (isLoading) {
    return (
      <div className="loading-state">
        <div className="loading-spinner"></div>
        <p>위스키를 검색중입니다...</p>
      </div>
    )
  }

  if (!results || results.length === 0) {
    return (
      <div className="no-results">
        <p>검색 결과가 없습니다.</p>
        <p>다른 키워드로 검색해보세요.</p>
      </div>
    )
  }

  return (
    <div className="results-list">
      <div className="results-header fade-in-up">
        <h2 className="results-title gradient-text">당신을 위한 위스키</h2>
        <p className="results-description">
          <span className="query-text">"{query}"</span>에 대한 맞춤 추천이에요.
        </p>
      </div>

      <div className="results-controls fade-in-up">
        <div className="filter-tabs no-scrollbar">
          <button className={`filter-tab ${filterType === null ? "active" : ""}`} onClick={() => setFilterType(null)}>
            전체
          </button>
          {types.map((type) => (
            <button
              key={type}
              className={`filter-tab ${filterType === type ? "active" : ""}`}
              onClick={() => setFilterType(type)}
            >
              {type}
            </button>
          ))}
        </div>

        <div className="sort-controls">
          <button className="btn btn-secondary" onClick={() => setShowFilterModal(true)}>
            <Filter size={14} />
          </button>
          <button
            className="btn btn-secondary"
            onClick={() => {
              const nextSort = sortBy === "match" ? "rating" : sortBy === "rating" ? "price" : "match"
              setSortBy(nextSort)
            }}
          >
            <ArrowUpDown size={14} />
            {getSortLabel()}
          </button>
        </div>
      </div>

      <div className="results-grid">
        {filteredResults.map((whiskey, index) => (
          <WhiskeyCard key={whiskey.id} whiskey={whiskey} rank={index + 1} />
        ))}
      </div>

      {filteredResults.length === 0 && (
        <div className="no-results">
          <p>해당 조건에 맞는 위스키가 없어요. 필터를 변경해보세요.</p>
        </div>
      )}

      <FilterModal
        isOpen={showFilterModal}
        onClose={() => setShowFilterModal(false)}
        sortBy={sortBy}
        setSortBy={setSortBy}
        filterType={filterType}
        setFilterType={setFilterType}
        types={types}
      />
    </div>
  )
}

export default ResultsList
