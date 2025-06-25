"use client"
import { X } from "lucide-react"
import "./FilterModal.css"

const FilterModal = ({ isOpen, onClose, sortBy, setSortBy, filterType, setFilterType, types }) => {
  if (!isOpen) return null

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="filter-modal-content" onClick={(e) => e.stopPropagation()}>
        <div className="filter-modal-header">
          <h3 className="filter-modal-title">필터 및 정렬</h3>
          <button className="btn btn-ghost" onClick={onClose}>
            <X size={20} />
          </button>
        </div>

        <div className="filter-modal-body">
          <div className="filter-section">
            <h4 className="filter-section-title">정렬 기준</h4>
            <div className="radio-group">
              {[
                { value: "match", label: "매치도순" },
                { value: "price", label: "가격순" },
                { value: "rating", label: "평점순" },
              ].map((option) => (
                <label key={option.value} className="radio-item">
                  <input
                    type="radio"
                    name="sort"
                    value={option.value}
                    checked={sortBy === option.value}
                    onChange={(e) => setSortBy(e.target.value)}
                    className="radio-input"
                  />
                  <span className="radio-label">{option.label}</span>
                </label>
              ))}
            </div>
          </div>

          <div className="filter-section">
            <h4 className="filter-section-title">위스키 타입</h4>
            <div className="radio-group">
              <label className="radio-item">
                <input
                  type="radio"
                  name="type"
                  value="all"
                  checked={filterType === null}
                  onChange={() => setFilterType(null)}
                  className="radio-input"
                />
                <span className="radio-label">전체</span>
              </label>
              {types.map((type) => (
                <label key={type} className="radio-item">
                  <input
                    type="radio"
                    name="type"
                    value={type}
                    checked={filterType === type}
                    onChange={() => setFilterType(type)}
                    className="radio-input"
                  />
                  <span className="radio-label">{type}</span>
                </label>
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default FilterModal
