"use client"
import { X, Star, Heart, Share2, ExternalLink } from "lucide-react"
import "./WhiskeyModal.css"

const WhiskeyModal = ({ whiskey, isOpen, onClose, liked, onLike, onShare }) => {
  if (!isOpen) return null

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal-content" onClick={(e) => e.stopPropagation()}>
        <div className="modal-header">
          <div className="modal-title-section">
            <h2 className="modal-title">{whiskey.name}</h2>
            <p className="modal-subtitle">
              {whiskey.distillery}, {whiskey.country}
            </p>
          </div>
          <button className="btn btn-ghost" onClick={onClose}>
            <X size={20} />
          </button>
        </div>

        <div className="modal-body">
          <div className="whiskey-image-section">
            <img
              src={
                whiskey.image_url ||
                `https://via.placeholder.com/300x400/333/fff?text=${encodeURIComponent(whiskey.name)}`
              }
              alt={whiskey.name}
              className="modal-whiskey-image"
            />
            <div className="match-badge badge">
              <span className="match-score">{Math.round(whiskey.match_score * 100)}%</span>
              <span>매치</span>
            </div>
          </div>

          <div className="rating-section">
            <div className="rating-stars">
              {Array.from({ length: 5 }).map((_, i) => (
                <Star key={i} size={16} className={i < Math.floor(whiskey.rating) ? "star-filled" : "star-empty"} />
              ))}
              <span className="rating-text">{whiskey.rating.toFixed(1)}</span>
            </div>

            <div className="action-buttons">
              <button className={`btn btn-ghost ${liked ? "liked" : ""}`} onClick={onLike}>
                <Heart size={16} />
              </button>
              <button className="btn btn-ghost" onClick={onShare}>
                <Share2 size={16} />
              </button>
            </div>
          </div>

          <div className="whiskey-details">
            <div className="detail-grid">
              <div className="detail-item">
                <span className="detail-label badge">종류</span>
                <span>{whiskey.type}</span>
              </div>
              <div className="detail-item">
                <span className="detail-label badge">도수</span>
                <span>{whiskey.abv}%</span>
              </div>
              {whiskey.age && (
                <div className="detail-item">
                  <span className="detail-label badge">숙성</span>
                  <span>{whiskey.age}년</span>
                </div>
              )}
              <div className="detail-item">
                <span className="detail-label badge">가격</span>
                <span>₩{whiskey.price.toLocaleString()}</span>
              </div>
            </div>
          </div>

          <div className="flavor-section">
            <h4 className="section-title">맛 프로필</h4>
            <div className="flavor-tags">
              {whiskey.flavor_profile.map((flavor) => (
                <span key={flavor} className="flavor-tag badge">
                  {flavor}
                </span>
              ))}
            </div>
          </div>

          <div className="description-section">
            <h4 className="section-title">설명</h4>
            <p className="description-text">{whiskey.description}</p>
          </div>

          <button className="purchase-button btn btn-primary">
            <ExternalLink size={16} />
            구매처 보기
          </button>
        </div>
      </div>
    </div>
  )
}

export default WhiskeyModal
